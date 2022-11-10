package jpabook.jpashop.controller

import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController (
    val itemService: ItemService
) {
    @GetMapping("/items/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    @PostMapping("/items/new")
    fun create(bookForm: BookForm): String {
        val book = Book(bookForm.author!!, bookForm.isbn!!)
        book.name = bookForm.name
        book.price = bookForm.price
        book.stockQuantity = bookForm.stockQuantity

        itemService.saveItem(book)
        return "redirect:/items"
    }

    @GetMapping("/items")
    fun list(model: Model): String {
        val items = itemService.findItems()
        model.addAttribute("items", items)
        return "items/itemList"
    }

    @GetMapping("/items/{itemId}/edit")
    fun updateItemForm(@PathVariable("itemId") itemId: Long, model: Model): String {
        val item: Book = itemService.findOne(itemId) as Book

        val bookForm: BookForm = BookForm(
            name = item.name,
            price = item.price,
            stockQuantity = item.stockQuantity,
            author = item.author,
            isbn = item.isbn,
            id = item.id
        )

        model.addAttribute("form", bookForm)
        return "items/updateItemForm"
    }

    @PostMapping("/items/{itemId}/edit")
    fun updateItem(@PathVariable("itemId") itemId: Long ,@ModelAttribute("form") bookForm: BookForm):String {
        // 준영속 엔티티, jpa관리 x, 더티 체킹 x
        itemService.updateItem(itemId, bookForm.name, bookForm.price!!)
        return "redirect:/items"
    }
}