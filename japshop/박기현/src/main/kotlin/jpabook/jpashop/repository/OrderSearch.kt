package jpabook.jpashop.repository

import jpabook.jpashop.domain.OrderStatus

class OrderSearch(
    var memberName: String,
    var orderStatus: OrderStatus,
) {
}
