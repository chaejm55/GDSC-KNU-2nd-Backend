package jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    name: String,
    price: Int,
    stockQuantity: Int,
    var artist: String = "",
    var etc: String = "",
) : Item(
    name = name,
    price = price,
    stockQuantity = stockQuantity
)