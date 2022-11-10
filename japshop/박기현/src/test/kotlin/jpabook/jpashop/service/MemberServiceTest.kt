package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class MemberServiceTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val memberService: MemberService,
) {

    @Test
    @DisplayName("회원가입이 정상적으로 동작한다.")
    fun 회원가입() {
        // given
        val member = Member("박기현", null)

        // when
        val savedId = memberService.join(member)

        // then
        assertThat(member).isEqualTo(memberRepository.findOne(savedId))
    }

    @Test
    @DisplayName("중복 회원이 예외처리 된다.")
    fun `중복 회원 예외`() {
        // given
        val member1 = Member("박기현", null)
        val member2 = Member("박기현", null)


        // when
        memberService.join(member1)
//        memberService.join(member2)

        // then
        assertThrows<IllegalStateException> { memberService.join(member2) }
            .apply {
                assertThat(message).isEqualTo("이미 존재하는 회원입니다")
            }
    }
}