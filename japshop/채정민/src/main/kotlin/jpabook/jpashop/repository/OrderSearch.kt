package jpabook.jpashop.repository

import jpabook.jpashop.domain.OrderStatus

class OrderSearch (
    val memberName: String?,
    val orderStatus: OrderStatus?
)