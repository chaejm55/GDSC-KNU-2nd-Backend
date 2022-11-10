package jpabook.jpashop.domain

import jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class Category(

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category,

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: MutableList<Item> = mutableListOf<Item>(),

    @OneToMany(mappedBy = "parent")
    val child: MutableList<Category> = mutableListOf<Category>(),

    @Id @GeneratedValue
    @Column(name = "category_id")
    val id: Long? = null,
) {
    fun addChildCategory(child: Category) {
        this.child.add(child)
        child.updateParent(this)
    }

    fun updateParent(parent: Category) {
        this.parent = parent
    }
}