package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import jpabook.jpashop.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(
    var name: String,

    var price: Int,

    var stockQuantity: Int,
) {
    @ManyToMany(mappedBy = "items")
    val categories: MutableList<Category> = mutableListOf()

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    val id: Long? = null

    //== 비즈니스 로직==//

    /**
     * stock 증가
     */
    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    /**
     * stock 감소
     */
    fun removeStock(quantity: Int) {
        val restStock = this.stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock
    }

}