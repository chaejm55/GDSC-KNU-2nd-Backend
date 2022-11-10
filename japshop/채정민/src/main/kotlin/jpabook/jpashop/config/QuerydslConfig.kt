package jpabook.jpashop.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class QuerydslConfig {
    @Configuration
    class QueryDslConfig {

        @PersistenceContext
        lateinit var em: EntityManager

        @Bean
        fun jpaQueryFactory(): JPAQueryFactory {
            return JPAQueryFactory(em)
        }
    }
}