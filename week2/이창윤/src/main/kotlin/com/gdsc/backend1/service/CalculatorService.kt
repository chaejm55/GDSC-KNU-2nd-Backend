package com.gdsc.backend1.service

import com.gdsc.backend1.service.constant.ErrorType
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CalculatorService {
    companion object {
        fun add(a: String, b: String): String {
            return try {
                (a.toFloat() + b.toFloat()).toString()
            } catch (e: NumberFormatException) {
                throw ResponseStatusException(ErrorType.NOT_A_NUMBER.status, ErrorType.NOT_A_NUMBER.message)
            }
        }

        fun subtract(a: String, b: String): String {
            return try {
                (a.toFloat() - b.toFloat()).toString()
            } catch (e: NumberFormatException) {
                throw ResponseStatusException(ErrorType.NOT_A_NUMBER.status, ErrorType.NOT_A_NUMBER.message)
            }
        }

        fun multiply(a: String, b: String): String {
            return try {
                (a.toFloat() * b.toFloat()).toString()
            } catch (e: NumberFormatException) {
                throw ResponseStatusException(ErrorType.NOT_A_NUMBER.status, ErrorType.NOT_A_NUMBER.message)
            }
        }

        fun divide(a: String, b: String): String {
            return try {
                if (b == "0" || b == "0.0") throw ResponseStatusException(ErrorType.DIVIDE_BY_ZERO.status, ErrorType.DIVIDE_BY_ZERO.message)
                else (a.toFloat() / b.toFloat()).toString()
            } catch (e: NumberFormatException) {
                throw ResponseStatusException(ErrorType.NOT_A_NUMBER.status, ErrorType.NOT_A_NUMBER.message)
            }
        }
    }
}