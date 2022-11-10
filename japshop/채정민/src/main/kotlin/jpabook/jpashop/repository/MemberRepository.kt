package jpabook.jpashop.repository

import jpabook.jpashop.domain.Member
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemberRepository (
    @PersistenceContext
    val em: EntityManager
) {
    fun save (member: Member) = em.persist(member)

    fun findOne(id: Long) : Member {
        return em.find(Member::class.java, id)
    }

    fun findAll() : MutableList<Member> {
        return em.createQuery("select m from Member m", Member::class.java)
            .resultList // java getResultList()
    }

    fun findByName(name: String) : MutableList<Member> {
        return em.createQuery(
            "select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
    }
}