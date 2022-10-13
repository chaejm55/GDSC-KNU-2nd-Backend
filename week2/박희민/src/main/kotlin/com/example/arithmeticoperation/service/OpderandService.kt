package com.example.arithmeticoperation.service

import org.springframework.stereotype.Service

@Service
class OpderandService {

    fun add(op1 : Int , op2:Int) : Int = op1+op2
    fun minus(op1 : Int , op2:Int) : Int = op1-op2
    fun mul(op1 : Int , op2:Int) : Int = op1*op2
    fun div(op1 : Int , op2:Int) : Int = op1/op2
}