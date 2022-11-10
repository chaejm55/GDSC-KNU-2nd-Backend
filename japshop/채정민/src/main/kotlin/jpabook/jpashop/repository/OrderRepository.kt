package jpabook.jpashop.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.domain.QMember
import jpabook.jpashop.domain.QOrder
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.Join
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.swing.text.MutableAttributeSet
import kotlin.jvm.internal.MutablePropertyReference0Impl
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Repository
class OrderRepository (
    @PersistenceContext
    private val em: EntityManager,
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    //querydsl, 동적 쿼리에 가장 적합
    fun findAll(orderSearch: OrderSearch): List<Order> {
        val order = QOrder.order
        val member = QMember.member

        return jpaQueryFactory
            .select(order)
            .from(order)
            .join(order.member, member)
            .where(statusEq(order, orderSearch.orderStatus!!),
                    nameLike(member, orderSearch.memberName!!))
            .limit(1000)
            .fetch()
    }

    private fun statusEq(order: QOrder, statusCond: OrderStatus): BooleanExpression? {
        if (statusCond == null) {
            return null
        }
        return order.status.eq(statusCond)
    }

    private fun nameLike(member: QMember, nameCond: String): BooleanExpression? {
        if (!StringUtils.hasText(nameCond)) {
            return null
        }
        return member.name.like(nameCond)
    }

    // jpql, 버그 발생 가능
    fun findAllByString(orderSearch: OrderSearch): List<Order> {
        var jpql = "select o From Order o join o.member m"
        var isFirstCondition = true
        //주문 상태 검색
        if (orderSearch.orderStatus != null) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
            jpql += " o.status = :status"
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.memberName)) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
            jpql += " m.name like :name"
        }
        var query = em.createQuery(jpql, Order::class.java)
            .setMaxResults(1000) //최대 1000건

        if (orderSearch.orderStatus != null) {
            query = query.setParameter("status", orderSearch.orderStatus)
        }
        if (StringUtils.hasText(orderSearch.memberName)) {
            query = query.setParameter("name", orderSearch.memberName)
        }

        return query.resultList
    }
}

    /**
     * jpa criteria
     */
//    fun findAllByCriteria(orderSearch: OrderSearch) : List<Order> {
//        val cb = em.criteriaBuilder
//        val cq = cb.createQuery(Order::class.java)
//        val o: Root<Order> = cq.from(Order::class.java)
//        val m: Join<JvmType.Object, JvmType.Object> = o.join("member", JoinType.INNER)
//
//        val criteria = mutableListOf<Predicate>()
//
//        if (orderSearch.orderStatus != null) {
//            val status = cb.equal(o.get<Predicate>("status"), orderSearch.orderStatus)
//            criteria.add(status)
//        }
//
//        if (StringUtils.hasText(orderSearch.memberName)) {
//            val name =
//                cb.like(m.get("name"), "%" + orderSearch.memberName + "%")
//            criteria.add(name)
//        }
//
//        cq.where(cb.and(criteria.toTypedArray(Predicate[criteria.size])))
//        val query = em.createQuery(cq).setMaxResults(1000);
//
//        return query.resultList
//    }
//}