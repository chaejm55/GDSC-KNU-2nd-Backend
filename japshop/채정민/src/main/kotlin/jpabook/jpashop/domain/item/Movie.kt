package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@DiscriminatorValue("M")
class Movie (director: String, actor: String): Item() {
    val director: String = director
    val actor: String = actor
}