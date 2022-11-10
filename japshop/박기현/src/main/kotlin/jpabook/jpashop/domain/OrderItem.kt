package jpabook.jpashop.domain

import jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class OrderItem(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item,

    val orderPrice: Int,

    val count: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null,

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    val id: Long? = null,
) {
    val totalPirce: Int
        get() = orderPrice * count

    fun updateOrder(order: Order) {
        this.order = order;
    }

    // 비즈니스 로직
    fun cancel() {
        this.item.addStock(count)
    }

    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem(
                item = item,
                orderPrice = orderPrice,
                count = count,
            )
            item.removeStock(count)

            return orderItem
        }
    }
}
