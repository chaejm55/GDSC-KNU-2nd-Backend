package jpabook.jpashop.repository

import jpabook.jpashop.domain.item.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ItemRepository (
    @PersistenceContext
    val em: EntityManager
) {
    fun save(item: Item) {
        if (item.id == null) {
            em.persist(item)
        } else {
            // 준영속 엔티티를 영속성 엔티티로 바꿈, 파라미터는 그대로지만 반환 값이 영속성 엔티티 
            // * 모든 속성 변경: 값이 없다면 null로 대체됨
            em.merge(item) 
        }
    }

    fun findOne(id: Long): Item {
        return em.find(Item::class.java, id)
    }

    fun findAll(): List<Item> {
        return em.createQuery("select i from Item i", Item::class.java)
            .resultList
    }
}