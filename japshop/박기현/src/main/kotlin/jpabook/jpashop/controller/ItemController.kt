package jpabook.jpashop.controller

import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController @Autowired constructor(
    private val itemService: ItemService,
) {

    @GetMapping("/items/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm.html"
    }

    @PostMapping("/items/new")
    fun create(form: BookForm): String {
        val book = Book(
            name = form.name!!,
            price = form.price!!,
            stockQuantity = form.stockQuantity!!,
            author = form.author!!,
            isbn = form.isbn!!,
        )

        itemService.saveItem(book)
        return "redirect:/items"
    }
}