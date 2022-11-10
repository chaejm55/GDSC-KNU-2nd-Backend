package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@DiscriminatorValue("A")
// 얘네도 테이블에 생성되는게 맞나??
class Album (artist: String, etc: String): Item() {
    val artist: String = artist
    val etc: String = etc
}