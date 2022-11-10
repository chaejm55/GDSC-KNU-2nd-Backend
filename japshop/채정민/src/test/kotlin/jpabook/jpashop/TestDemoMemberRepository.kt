package jpabook.jpashop

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

// 스프링 튜토리얼 참조
@DataJpaTest
class TestDemoMemberRepository @Autowired constructor(
    val em: EntityManager,
    val demoMemberRepository: DemoMemberRepository
) {
    @Test
    @Transactional
    fun `save member`() {
        val member: DemoMember = DemoMember("memberA")
        em.persist(member)
        em.flush()

        val findMember: DemoMember? = demoMemberRepository.findByIdOrNull(member.id!!) // 그냥 findByID는 Optional<> 리턴

        Assertions.assertThat(findMember?.id).isEqualTo(member.id)
        Assertions.assertThat(findMember?.username).isEqualTo(member.username)
        Assertions.assertThat(findMember).isEqualTo(member)
    }
}