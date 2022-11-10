package jpabook.jpashop.controller

import javax.validation.constraints.NotEmpty

class MemberForm (
    // validation 사용시 @field: 명시 그래야 필드에도 validation이 들어감
    @field: NotEmpty(message = "회원 이름은 필수입니다.")
    val name: String? = null,
    val city: String? = null,
    val street: String? = null,
    val zipcode: String? = null
)
