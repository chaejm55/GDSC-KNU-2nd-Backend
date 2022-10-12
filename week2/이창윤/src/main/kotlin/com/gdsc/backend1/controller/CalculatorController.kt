package com.gdsc.backend1.controller

import com.gdsc.backend1.service.CalculatorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculatorController {

    @GetMapping("/sum")
    fun sum(@RequestParam a:String, @RequestParam b:String) : String = CalculatorService.add(a,b)

    @GetMapping("/difference")
    fun difference(@RequestParam a:String, @RequestParam b:String) : String = CalculatorService.subtract(a,b)

    @GetMapping("/product")
    fun product(@RequestParam a:String, @RequestParam b:String) : String = CalculatorService.multiply(a,b)

    @GetMapping("/quotient")
    fun quotient(@RequestParam a:String, @RequestParam b:String) : String = CalculatorService.divide(a,b)

}