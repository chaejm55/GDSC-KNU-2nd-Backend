package jpabook.jpashop.service

import jpabook.jpashop.domain.Delivery
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderItem
import jpabook.jpashop.domain.item.Item
import jpabook.jpashop.repository.ItemRepository
import jpabook.jpashop.repository.MemberRepository
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService (
    val orderRepository: OrderRepository,
    val memberRepository: MemberRepository,
    val itemRepository: ItemRepository
) {

    /**
     * 주문
     */
    @Transactional
    fun order (memberId: Long, itemId: Long, count: Int): Long {
        // 엔티티 조회
        val member = memberRepository.findOne(memberId)
        val item = itemRepository.findOne(itemId)

        // 배송정보 생성
        val delivery: Delivery = Delivery(
            order = null,
            address = member.address,
            status = null,
        )

        // 주문상품 생성, companion object 사용
        val orderItem: OrderItem = OrderItem.createOrderItem(item, item.price!!, count)

        // 주문 생성
        val order: Order = Order.createOrder(member, delivery, listOf(orderItem))


        // TODO:추후 수정 필요
        // 그냥 땜빵용...
        orderItem.order = order
        delivery.order = order


        // 주문 저장
        orderRepository.save(order)

        return order.id!!
    }
    /**
     * 주문취소
     */
    @Transactional
    fun cancelOrder(orderId: Long) {
        // 주문 엔티티 조회
        val order = orderRepository.findOne(orderId)
        // 도메인 모델 패턴: 엔티티가 비즈니스 로직을 가짐
        // 주문 취소
        order.cancel()
    }


    // 검색
    fun findOrders(orderSearch: OrderSearch): List<Order> {
//        println("list=?")
        val list = orderRepository.findAllByString(orderSearch)
//        try {
//            println("list.size=${list.size}")
//            println("orderItem.size=${list[0].orderItems.size}")
//            println("orderItem=${list[0].orderItems[0].item?.name}")
//        } catch (e: Exception) {
//            println(e.message)
//        }
        return list
    }
}