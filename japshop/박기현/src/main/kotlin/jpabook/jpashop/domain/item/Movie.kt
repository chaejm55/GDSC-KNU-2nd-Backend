package jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie(
    name: String,
    price: Int,
    stockQuantity: Int,
    val director: String = "",
    val actor: String = "",
) : Item(
    name = name,
    price = price,
    stockQuantity = stockQuantity
)
