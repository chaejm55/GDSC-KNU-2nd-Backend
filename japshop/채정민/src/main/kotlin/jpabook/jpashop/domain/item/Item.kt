package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import jpabook.jpashop.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item () {
    // secondary constructor 사용으로 var 사용?
    var name: String? = null
    var price: Int? = null
    var stockQuantity: Int? = null

    // mutable list로 해야 연관 관계 매핑 오류 안 남
    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category>? = mutableListOf<Category>()

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    var id: Long? = null

    constructor(name: String, price: Int, stockQuantity: Int, categories: MutableList<Category>) : this() {
        this.name = name
        this.price = price
        this.stockQuantity = stockQuantity
        this.categories = categories
    }

    //==비즈니스 로직==//
    /**
     * stock 증가
     */
    fun addStock(quantity: Int) {
        this.stockQuantity = this.stockQuantity?.plus(quantity) // safe call 연산 후 값은 해당 프로퍼티 저장 x?
    }
    /**
     * stock 감소
     */
    fun removeStock(quantity: Int) {
        val restStock = this.stockQuantity?.minus(quantity)
        if (restStock != null) {
            if (restStock < 0) {
                throw NotEnoughStockException("need more stock")
            }
        }
        this.stockQuantity = restStock
    }
}
