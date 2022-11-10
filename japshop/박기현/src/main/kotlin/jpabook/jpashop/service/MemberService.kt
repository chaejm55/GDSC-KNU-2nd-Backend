package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
) {

    // 회원가입
    @Transactional
    fun join(member: Member): Long {
        validateDuplicatedMember(member)
        memberRepository.save(member)
        return member.id!!
    }

    private fun validateDuplicatedMember(member: Member) {
        // EXCEPTION
        val members = memberRepository.findByName(member.name)

        if (members.isNotEmpty()) {
            throw IllegalStateException("이미 존재하는 회원입니다")
        }
    }

    // 회원 전체 조회
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Member {
        return memberRepository.findOne(memberId)
    }
}