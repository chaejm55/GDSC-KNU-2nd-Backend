package com.gdsc.backend1.service

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CalcService {
    fun plus(num1: String, num2: String) : Float = num1.toFloat() + num2.toFloat()

    fun minus(num1: String, num2: String) : Float = num1.toFloat() - num2.toFloat()

    fun multiplication(num1: String, num2: String) : Float = num1.toFloat() * num2.toFloat()

    fun division (num1: String, num2: String) : Float {
        if (num2.toFloat() == 0.0F) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "0으로 나눌 수 없습니다.")
        } else {
            return num1.toFloat() / num2.toFloat()
        }
    }
}