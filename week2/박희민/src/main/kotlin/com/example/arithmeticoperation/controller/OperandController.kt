package com.example.arithmeticoperation.controller

import com.example.arithmeticoperation.service.OpderandService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OperandController {

    @Autowired
    private lateinit var calculatorService : OpderandService

    @GetMapping("/operands/{op1}/{op2}")
    @ResponseBody
    fun calculateOperand(@PathVariable("op1") op1:Int , @PathVariable("op2") op2:Int) : Map<String,Int> {
       var a = mutableMapOf<String,Int>()
        a.put("더하기",calculatorService.add(op1,op2))
        a.put("빼기", calculatorService.minus(op1,op2))
        a.put("곱하기",calculatorService.mul(op1, op2))
        a.put("나누기",calculatorService.div(op1,op2))
        return a
    }


}