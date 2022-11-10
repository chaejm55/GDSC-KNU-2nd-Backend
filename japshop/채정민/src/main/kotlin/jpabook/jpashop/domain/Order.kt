package jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val orderItems: List<OrderItem>,

    val orderDate: LocalDateTime,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    val delivery: Delivery,

    @Enumerated(EnumType.STRING) // ORDINAL 쓰면 추가 시 인덱스 밀림
    var status: OrderStatus, // 주문상태 [ORDER, CANCEL]


) {
    @Id @GeneratedValue
    @Column(name = "order_id")
    val id: Long? = null

    //==생성 메서드==//
    companion object {
        fun createOrder(member: Member, delivery: Delivery, orderItems: List<OrderItem>): Order {
            return Order(
                member = member,
                delivery = delivery,
                orderItems = orderItems, // 가변 파라미터 대신?
                status = OrderStatus.ORDER,
                orderDate = LocalDateTime.now(),
            )
        }
    }

    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }
        this.status = OrderStatus.CANCEL
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    fun getTotalPrice(): Int {
        return orderItems.sumOf { it.getTotalPrice() }
    }
}

