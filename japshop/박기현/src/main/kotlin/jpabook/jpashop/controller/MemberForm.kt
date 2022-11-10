package jpabook.jpashop.controller

import javax.validation.constraints.NotEmpty

class MemberForm(
    // 그냥 NotEmpty를 하게되면 동작하지 않는다. field에 Annotation이 붙도록 수정해줘야한다.
    @field:NotEmpty(message = "회원 이름은 필수입니다.")
    var name: String = "",
    var city: String? = null,
    var street: String? = null,
    var zipcode: String? = null,
) {

}
