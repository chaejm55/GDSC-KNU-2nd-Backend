package jpabook.jpashop.repository

import jpabook.jpashop.service.MemberService
import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberRepositoryTest (
    @Autowired
    val memberService: MemberService,
    @Autowired
    val memberRepository: MemberRepository
) {
    @Test
    fun 회원가입() { // 이게 맞나????, 코틀린 스타일 궁금
        //given
        val member: Member = Member("kim", Address("1", "2", "3"), null)

        //when
        val savedId: Long? = memberService.join(member)

        //then
        assertThat(member).isEqualTo(memberRepository.findOne(savedId!!))
    }

    @Test
    fun 중복_회원_예외() { // 이게 맞나????, 코틀린 스타일 궁금
        //given
        val member1: Member = Member("kim", Address("1", "2", "3"), null)
        val member2: Member = Member("kim", Address("1", "2", "3"), null)

        //when
        memberService.join(member1)

        //then
        assertThatThrownBy {
            memberService.join(member2)
        }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("이미 존재하는 회원입니다.")
    }
}