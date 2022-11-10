package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@DiscriminatorValue("B")
class Book(author: String, isbn: String): Item() {
    val author: String = author
    val isbn: String = isbn
}