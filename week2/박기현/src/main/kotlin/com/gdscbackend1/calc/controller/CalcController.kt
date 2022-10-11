package com.gdscbackend1.calc.controller

import com.gdscbackend1.calc.service.CalcService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CalcController private constructor(private val calcService: CalcService) {

//    @GetMapping("/plus")
//    fun plus(@RequestParam("a") number1: String, @RequestParam("b") number2: String): String{
//        return ( number1.toInt() + number2.toInt() ).toString()
//    }
    @GetMapping("/plus")
    fun plus(@RequestParam a: String, @RequestParam b: String): String = calcService.plus(a, b)

    @GetMapping("minus")
    fun minus(@RequestParam a: String, @RequestParam b: String): String = calcService.minus(a, b)

    @GetMapping("multiply")
    fun multiply(@RequestParam a: String, @RequestParam b: String): String = calcService.multiply(a, b)

    @GetMapping("divide")
    fun divide(@RequestParam a: String, @RequestParam b: String): String = calcService.divide(a, b)
}