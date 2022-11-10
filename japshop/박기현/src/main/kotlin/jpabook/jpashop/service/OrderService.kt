package jpabook.jpashop.service

import jpabook.jpashop.domain.Delivery
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderItem
import jpabook.jpashop.repository.ItemRepository
import jpabook.jpashop.repository.MemberRepository
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository,
) {

    // 주문
    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long {

        // 엔티티 조회
        val member = memberRepository.findOne(memberId)
        val item = itemRepository.findOne(itemId)

        // 배송정보 생성
        val delivery = Delivery(address = member.address!!)

        // 주문상품 생성
        val orderItem = OrderItem.createOrderItem(item = item, orderPrice = item.price, count = count)

        // 주문 생성
        val order = Order.createOrder(
            member = member,
            delivery = delivery,
            orderItems = arrayOf(orderItem),
        )

        // 주문 저장
        orderRepository.save(order)

        return order.id!!
    }

    // 취소
    @Transactional
    fun cancelOrder(orderId: Long) {
        // 주문 엔티티 조회
        val order = orderRepository.findOne(orderId)

        // 주문 취소
        order.cancel()
    }

    // 검색
    fun findOrders(orderSearch: OrderSearch): List<Order> {
        return orderRepository.findAll(orderSearch)
    }
}