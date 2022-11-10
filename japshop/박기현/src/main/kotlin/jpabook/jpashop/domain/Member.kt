package jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Member(

    val name: String,

    @Embedded
    val address: Address?,

    @OneToMany(mappedBy = "member")
    val orders: MutableList<Order> = mutableListOf(),

    @Id @GeneratedValue @Column(name = "member_id")
    val id: Long? = null,
) {
}