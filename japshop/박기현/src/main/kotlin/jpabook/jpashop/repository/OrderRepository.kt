package jpabook.jpashop.repository

import jpabook.jpashop.domain.Order
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class OrderRepository(
    private val em: EntityManager,
) {
    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id);
    }

    // TODO: QueryDSL 로 리팩토링 해야함!!
    fun findAll(orderSearch: OrderSearch): List<Order> {
        return em.createQuery(
            "select o from Order o join o.member m" +
                    " where o.status = :status" +
                    " and m.name like :name", Order::class.java
        )
            .setParameter("status", orderSearch.orderStatus)
            .setParameter("name", orderSearch.memberName)
            .setMaxResults(1000)
            .resultList
    }
}