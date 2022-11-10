package jpabook.jpashop

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoMemberRepository: CrudRepository<DemoMember, Long> {
}