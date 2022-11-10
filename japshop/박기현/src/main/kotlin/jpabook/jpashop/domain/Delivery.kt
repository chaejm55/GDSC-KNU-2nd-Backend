package jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Delivery(

    @Embedded
    val address: Address,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.READY,

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order? = null,

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    val id: Long? = null,
) {
    fun updateOrder(order: Order) {
        this.order = order
    }


}
