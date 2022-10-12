package com.gdsc.backend1.controller

import com.gdsc.backend1.dto.RequestBodyDto
import com.gdsc.backend1.dto.ResponseDto
import com.gdsc.backend1.service.CalcService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController // JSON 반환
class CalcController(private val calcService: CalcService) {

    @GetMapping("/plus")
    fun plusController(@RequestParam num1 : String, @RequestParam num2 : String) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.plus(num1, num2)))

    @PostMapping("/plus")  // post는 JSON으로 요청받기
    fun postPlusController(@RequestBody request: RequestBodyDto) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.plus(request.num1, request.num2)))

    @GetMapping("/minus")
    fun minusController(@RequestParam num1 : String, @RequestParam num2 : String) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.minus(num1, num2)))

    @PostMapping("/minus")
    fun postMinusController(@RequestBody request: RequestBodyDto) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.minus(request.num1, request.num2)))

    @GetMapping("/multiplication")
    fun multiplicationController(@RequestParam num1 : String, @RequestParam num2 : String) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.multiplication(num1, num2)))

    @PostMapping("/multiplication")  // restful하게 하려면 명사로????
    fun postMultiplicationController(@RequestBody request: RequestBodyDto) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.multiplication(request.num1, request.num2)))

    @GetMapping("/division")
    fun divisionController(@RequestParam num1 : String, @RequestParam num2 : String) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.division(num1, num2)))

    @PostMapping("/division")
    fun postDivisionController(@RequestBody request: RequestBodyDto) : ResponseEntity<Any> =
        ResponseEntity.ok().body(ResponseDto(calcService.division(request.num1, request.num2)))
}