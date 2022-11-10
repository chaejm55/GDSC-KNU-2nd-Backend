package jpabook.jpashop.domain

import jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class Category (
    val name: String,

    @ManyToMany
    // annotation 안에 annotation 으로 값 사용 불가, 코틀린 스타일로 변경
    @JoinTable(name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")])
    val items: MutableList<Item>,

    @ManyToOne
    @JoinColumn(name = "parent_id")
    val parent: Category,

    @OneToMany(mappedBy = "parent")
    val child: MutableList<Category>,
) {
    @Id @GeneratedValue
    @Column(name = "category_id")
    val id: Long? = null
}