package com.gdscbackend1.calc.service

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.server.ResponseStatusException

@Service
class CalcService{
    fun plus(a: String, b: String): String = (a.toFloat() + b.toFloat()).toString()

    fun minus(a: String, b: String): String = (a.toFloat() - b.toFloat()).toString()

    fun multiply(a: String, b: String): String = (a.toFloat() * b.toFloat()).toString()

    fun divide(a: String, b: String): String {
        return when(b) {
            "0" -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "0으로 나눌 수 없습니다.",
                IllegalArgumentException()
            )
            else -> (a.toFloat() / b.toFloat()).toString()
        }
    }

}