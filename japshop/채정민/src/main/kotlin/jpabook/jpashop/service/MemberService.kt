package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService (
    @Autowired
    val memberRepository: MemberRepository
) {
    /**
     * 회원 가입
     */

    @Transactional
    fun join(member: Member) : Long? {
        validateDuplicateMember(member) // 중복 회원 검출
        memberRepository.save(member)
        return member.id
    }

    private fun validateDuplicateMember(member: Member) {
        val findMembers: MutableList<Member> = memberRepository.findByName(member.name)
        if (findMembers.isNotEmpty()) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    // 회원 전체 조회
    fun findMembers() : MutableList<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Member {
        return memberRepository.findOne(memberId)
    }
}