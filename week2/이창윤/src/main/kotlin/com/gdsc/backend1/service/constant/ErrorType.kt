package com.gdsc.backend1.service.constant

import org.springframework.http.HttpStatus

enum class ErrorType(val status : HttpStatus, val message : String) {
    NOT_A_NUMBER(HttpStatus.BAD_REQUEST, "숫자를 입력하세요."),
    DIVIDE_BY_ZERO(HttpStatus.BAD_REQUEST, "0으로 나눌 수 없습니다.")
}