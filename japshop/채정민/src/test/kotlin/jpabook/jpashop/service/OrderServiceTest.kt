package jpabook.jpashop.service

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.exception.NotEnoughStockException
import jpabook.jpashop.repository.OrderRepository
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class OrderServiceTest(
    @Autowired val em: EntityManager,
    @Autowired val orderService: OrderService,
    @Autowired val orderRepository: OrderRepository
) {
    @Test
    fun 상품주문() {
        //given
        val member: Member = createMember()

        val name = "시골 JPA"
        val price = 10000
        val stockQuantity = 10

        val book: Book = createBook(name, price, stockQuantity)

        val orderCount = 2

        //when
        val orderId: Long = orderService.order(member.id!!, book.id!!, orderCount)

        //then
        val getOrder: Order = orderRepository.findOne(orderId)
        assertThat(getOrder.status).isEqualTo(OrderStatus.ORDER)
        assertThat(getOrder.orderItems.size).isEqualTo(1)
        assertThat(getOrder.getTotalPrice()).isEqualTo(price * orderCount)
        assertThat(book.stockQuantity).isEqualTo(stockQuantity - orderCount)
    }

    @Test
    fun 주문취소() {
        //given
        val member: Member = createMember()

        val name = "시골 JPA"
        val price = 10000
        val stockQuantity = 10

        val book: Book = createBook(name, price, stockQuantity)

        val orderCount = 2

        val orderId = orderService.order(member.id!!, book.id!!, orderCount)

        //when
        orderService.cancelOrder(orderId)

        //then
        val getOrder: Order = orderRepository.findOne(orderId)
        println("count=${getOrder.orderItems[0].count}")

        assertThat(getOrder.status).isEqualTo(OrderStatus.CANCEL)
        assertThat(book.stockQuantity).isEqualTo(stockQuantity)
    }

    @Test
    fun 상품주문_재고수량초과() {
        //given
        val member: Member = createMember()

        val name = "시골 JPA"
        val price = 10000
        val stockQuantity = 10

        val book: Book = createBook(name, price, stockQuantity)

        val orderCount = stockQuantity + 1

        //when

        //then
        assertThatThrownBy {
            orderService.order(member.id!!, book.id!!, orderCount)
        }
            .isExactlyInstanceOf(NotEnoughStockException::class.java)
            .hasMessage("need more stock")
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book: Book = Book("저자", "11111")
        book.name = name
        book.price = price
        book.stockQuantity = stockQuantity
        em.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member: Member = Member("회원1", Address("서울", "강가", "12345"), null)
        em.persist(member)
        return member
    }
}