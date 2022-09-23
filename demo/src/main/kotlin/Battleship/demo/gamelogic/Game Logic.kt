package Battleship.battleship

fun main() {
    GameManager()
}

enum class ShipOrientation{
    HORIZONTAL,
    VERTICAL
}
enum class ShipType(var size:Int){
    DESTROYER(2),
    SUBMARINE(3),
    CRUISER(3),
    BATTLESHIP(4),
    CARRIER(5)
}

//Manages the game in its whole
fun GameManager(){
    val grid = Grid(10,10)

    val player1 = PlayerGame(1)
    val player2 = PlayerGame(2)

    var ship = Ship(1, ShipType.BATTLESHIP, false)
    do{
        println("Select row")
        var row  = Integer.valueOf(readLine())-1

        println("Select col")
        var column  = Integer.valueOf(readLine())-1

        var shipPlacement =  ShipPlacement(ShipOrientation.VERTICAL, Pair(row, column))
        if(grid.checkPositionFree(ship, shipPlacement)){
            grid.placeShip(ship, shipPlacement)
        }
        else{
            println("Position not valid")

        }
        printGrid(grid)

    }while(true)
}

fun printGrid(grid:Grid){
    printIntermediaryLine(grid.colSize)
    for (r in 0 until grid.rowSize){
        for (c in 0 until grid.colSize){
            var ship = grid.grid[r][c]?.ship
            if(ship != null){
                print("|  ${ship.id} ")
            }
            else{
                print("|    ")
            }
        }
        println()
        printIntermediaryLine(grid.colSize)
    }
}

fun printIntermediaryLine(colSize : Int){
    for(c in 1..colSize){
        print("*----")
    }
    print("*")
    println()
}


//Game for one player
class PlayerGame(var playerID: Int) {
    var ships = mutableListOf<Ship>()   //Ships each player has
    var score : Int = 0


    //PLayer may place ships anywhere they want as long as they dont overlap obviously
    fun placeShip(position : Pair<Int, Char>){


    }
}


data class Tile(var isFree : Boolean = true, var position : Pair<Int, Int>, var ship:Ship?)

class Grid(val rowSize: Int, val colSize: Int){

    var grid: Array<Array<Tile?>> = Array(rowSize) { row ->
        Array(colSize) { col ->
            Tile(true, Pair(row, col), null)
        }
    }

    fun placeShip(ship : Ship, shipPlacement: ShipPlacement){
        if(checkPositionFree(ship, shipPlacement)){
            //place ship
            //mark all tiles as having the ship
            markAllTiles(ship, shipPlacement)
        }
    }

    private fun markAllTiles(ship: Ship, shipPlacement: ShipPlacement) {
        if(shipPlacement.shipOrientation == ShipOrientation.HORIZONTAL){
            var lastShipColumn = shipPlacement.centerPosition.second +  ship.shipType.size
            for (col in shipPlacement.centerPosition.second until lastShipColumn){
                markTile(Pair(shipPlacement.centerPosition.first, col), ship)

            }
        }
        else if(shipPlacement.shipOrientation == ShipOrientation.VERTICAL){
            var lastShipRow = shipPlacement.centerPosition.first +  ship.shipType.size
            for (row in shipPlacement.centerPosition.first until lastShipRow){
                markTile(Pair(row, shipPlacement.centerPosition.second), ship)
            }
        }
    }

    private fun markTile(pair: Pair<Int, Int>, ship: Ship) {
        grid[pair.first][pair.second]!!.ship = ship
        grid[pair.first][pair.second]!!.isFree = false
    }

    //Checks if the positions occupied by the ship dont overlap other ships
    fun checkPositionFree(ship:Ship, shipPlacement: ShipPlacement): Boolean {
        return if(shipPlacement.shipOrientation == ShipOrientation.HORIZONTAL){
            var shipCol = shipPlacement.centerPosition.second
            checkPositionHorizontally(shipCol, ship.shipType.size, shipPlacement.centerPosition.first)
        } else{
            var shipRow = shipPlacement.centerPosition.first
            checkPositionVertically(shipRow, ship.shipType.size, shipPlacement.centerPosition.second)
        }
    }

    fun checkPositionVertically(shipRow : Int, shipSize: Int, secondShipPosition : Int) : Boolean{
        for(row in shipRow..shipRow+shipSize){
            var tile = grid[row][secondShipPosition]
            if(!tile!!.isFree) return false
        }
        return true
    }

    fun checkPositionHorizontally(shipCol : Int, shipSize: Int, firstShipPosition : Int) : Boolean{
        for(column in shipCol..shipCol+shipSize){
            var tile = grid[firstShipPosition][column]
            if(!tile!!.isFree) return false
        }
        return true
    }
}

data class ShipPlacement(val shipOrientation: ShipOrientation, val centerPosition: Pair<Int, Int>)

open class Ship(
    val id : Int,
    val shipType : ShipType,
    var isSunk : Boolean,
)

