package jpabook.jpashop.controller

class BookForm(
    var id: Long? = null,
    var name: String? = null,
    var price: Int? = null,
    var stockQuantity: Int? = null,

    var author: String? = null,
    var isbn: String? = null,
) {
}