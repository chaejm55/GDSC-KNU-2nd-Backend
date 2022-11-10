package jpabook.jpashop

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class DemoMember ( // 코틀린 방식, data class는 불변객체라 entity로 사용 불가능
    val username: String,
    @Id @GeneratedValue
    val id: Long? = null, // db insert 전 까지는 값이 null이라 nullable 해야함
)