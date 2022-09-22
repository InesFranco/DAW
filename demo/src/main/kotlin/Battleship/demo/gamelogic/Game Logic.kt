package Battleship.battleship

fun main() {
    GameManager()
}

enum class ShipOrientation{
    HORIZONTAL,
    VERTICAL
}
enum class ShipType{
    DESTROYER,
    SUBMARINE,
    CRUISER,
    BATTLESHIP,
    CARRIER
}

//Manages the game in its whole
fun GameManager(){
    val grid = Grid(3,3)

    val player1 = PlayerGame(1)
    val player2 = PlayerGame(2)

    do{
        println("Select row")
        var row  = Integer.valueOf(readLine())

        println("Select col")
        var column  = readLine()

        if(grid.checkPositionFree()){
            var position = Pair<row, column>
            player1.placeShip(Pair<row, col>())
        }

    }while(true)
}

fun printGrid(grid:Grid, ships : List<Ship>){
    printIntermediaryLine(grid.colSize)
    for (r in 1..grid.rowSize){
        for (c in 1..grid.colSize){
            print("    ")
        }
        println()
        printIntermediaryLine(grid.colSize)
    }
}

fun printIntermediaryLine(colSize : Int){
    for(c in 1..colSize){
        print("*---")
    }
    print("*")
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
            Tile(true, Pair(row, col))
        }
    }

    fun placeShip(ship : Ship, shipPlacement: ShipPlacement){
        if(checkPositionFree(ship, shipPlacement)){
            //place ship
        }
    }

    //Checks if the positions occupied by the ship dont overlap other ships
    fun checkPositionFree(ship:Ship, shipPlacement: ShipPlacement): Boolean {
        if(shipPlacement.shipOrientation == ShipOrientation.HORIZONTAL){
            var shipCol = shipPlacement.centerPosition.second
            for(column in shipCol..shipCol+ship.size){
                var tile = grid[shipPlacement.centerPosition.first][column]
                if(tile){

                }

            }
        }

    }

}

data class ShipPlacement(val shipOrientation: ShipOrientation, val centerPosition: Pair<Int, Int>)

open class Ship(
    val id : Int,
    val shipType : ShipType,
    val size: Int,
    val positions : List<Pair<Int, Int>>?,        //list of all the positions under the ship
    val shipOrientation: ShipOrientation,
    var isSunk : Boolean,
)

