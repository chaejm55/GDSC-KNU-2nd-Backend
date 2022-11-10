package jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String,
    price: Int,
    stockQuantity: Int,
    var author: String = "",
    var isbn: String = "",
) : Item(
    name = name,
    price = price,
    stockQuantity = stockQuantity
)
