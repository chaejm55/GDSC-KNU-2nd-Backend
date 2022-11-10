package jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val orderItems: MutableList<OrderItem> = mutableListOf<OrderItem>(),

    val orderDate: LocalDateTime = LocalDateTime.now(), // 주문 시간

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER, // 주문 상태 [ORDER, CANCEL]

    @Id @GeneratedValue
    @Column(name = "order_id")
    val id: Long? = null,
) {
    // 연관관계 메서드
    fun updateMember(member: Member) {
        this.member = member
        member.orders.add(this)
    }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.updateOrder(this)
    }

    fun updateDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.updateOrder(this)
    }

    fun updateStatus(status: OrderStatus) {
        this.status = status
    }

    // 비즈니스 로직
    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }

        this.updateStatus(OrderStatus.CANCEL)

        this.orderItems.map { orderItem -> orderItem.cancel() }
    }

    // 조회 로직
    val totalPrice: Int
        get() {
            return this.orderItems.stream().mapToInt(OrderItem::totalPirce).sum()
        }

    // 생성 메서드
    companion object {
        fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            val order = Order(
                member = member,
                delivery = delivery,
            )
            orderItems.map { orderItem -> order.addOrderItem(orderItem) }

            return order
        }
    }
}
