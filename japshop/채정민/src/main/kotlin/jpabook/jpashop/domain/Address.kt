package jpabook.jpashop.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Address (
    @Column // 에러 발생 방지를 위해 컬럼으로 사용 명시
    val city: String?,
    val street: String?,
    val zipcode: String?
)