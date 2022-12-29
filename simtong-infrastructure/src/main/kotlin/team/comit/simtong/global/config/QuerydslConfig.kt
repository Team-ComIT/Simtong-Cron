package team.comit.simtong.global.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

/**
 *
 * JPAQueryFactory를 Bean 등록하는 QuerydslConfig
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Configuration
class QuerydslConfig(
    private val entityManage: EntityManager
) {

    @Bean
    fun queryFactory() : JPAQueryFactory = JPAQueryFactory(entityManage)
}