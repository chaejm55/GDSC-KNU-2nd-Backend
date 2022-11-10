package jpabook.jpashop.domain

import jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class OrderItem (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order?,

    val orderPrice: Int?,
    val count: Int?,
) {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    val id: Long? = null

    //==생성 메서드==//
    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem: OrderItem = OrderItem(
                item = item,
                order = null,
                orderPrice = orderPrice,
                count = count,
            )
            item.removeStock(count)
            return orderItem
        }
    }

    //==비즈니스 로직==//
    fun cancel() { // 주문 취소 시 재고 수량 복구
        item?.addStock(count!!)
    }

    /**
     * 주문상품 전체 가격 조회
     */
    fun getTotalPrice(): Int {
        return orderPrice?.times(count!!) ?: 0
    }
}
