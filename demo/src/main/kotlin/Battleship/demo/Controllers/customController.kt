package Battleship.demo.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class StudentOutputModel(
    var name: String,
    var number: Int
)

@RestController
class customController{

    @GetMapping("/0")
    fun route1(
        @RequestParam id : Int
    ) = "Hello $id"

    // Using output models
    @GetMapping("1")
    fun handler1() = StudentOutputModel("Alice", 12345)
}