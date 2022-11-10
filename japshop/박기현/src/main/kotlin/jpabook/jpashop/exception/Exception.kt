package jpabook.jpashop.exception

class NotEnoughStockException(
    message: String,
) : RuntimeException(message) {
}