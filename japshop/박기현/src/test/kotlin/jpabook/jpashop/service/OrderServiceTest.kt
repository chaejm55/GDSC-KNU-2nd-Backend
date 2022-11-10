package jpabook.jpashop.service

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.exception.NotEnoughStockException
import jpabook.jpashop.repository.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class OrderServiceTest @Autowired constructor(
    private val em: EntityManager,
    private val orderService: OrderService,
    private val orderRepository: OrderRepository,

    ) {
    @Test
    @DisplayName("상품 주문")
    fun 상품주문() {
        // given
        val member = createMember()

        val book = createBook("시골 JPA", 10000, 10)

        val orderCount = 2

        // when
        val orderId = orderService.order(
            memberId = member.id!!,
            itemId = book.id!!,
            count = orderCount,
        )

        // then
        val order = orderRepository.findOne(orderId)

        assertThat(order.status).isEqualTo(OrderStatus.ORDER)
        assertThat(order.orderItems).hasSize(1)
        assertThat(book.price * orderCount).isEqualTo(order.totalPrice)
        assertThat(10 - orderCount).isEqualTo(book.stockQuantity)
    }

    @Test
    @DisplayName("상품 주문을 초과해서 주문")
    fun 상품주문_재고수량초과() {
        // given
        val member = createMember()
        val book = createBook("시골 JPA", 10000, 10)
        val orderCount = 11

        // when


        // then
        assertThrows<NotEnoughStockException> { orderService.order(member.id!!, book.id!!, orderCount) }
    }

    @Test
    @DisplayName("주문이 취소된다.")
    fun 주문취소() {
        // given
        val member = createMember()
        val book = createBook("시골 JPA", 10000, 10)
        val orderCount = 2

        val orderId = orderService.order(member.id!!, book.id!!, orderCount)

        // when
        orderService.cancelOrder(orderId)

        // then
        val order = orderRepository.findOne(orderId)

        assertThat(order.status).isEqualTo(OrderStatus.CANCEL)
        assertThat(book.stockQuantity).isEqualTo(10)
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book = Book(
            name = name,
            price = price,
            stockQuantity = stockQuantity
        )

        em.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member = Member(
            name = "회원1",
            address = Address("서울", "강가", "123-123")
        )

        em.persist(member)
        return member
    }
}
