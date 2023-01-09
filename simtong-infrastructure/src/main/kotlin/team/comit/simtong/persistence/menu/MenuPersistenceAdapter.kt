package team.comit.simtong.persistence.menu

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.outbound.port.QueryMenuPort
import team.comit.simtong.persistence.menu.entity.QMenuJpaEntity.menuJpaEntity as menu
import team.comit.simtong.persistence.menu.mapper.MenuMapper
import java.time.LocalDate

/**
 *
 * 메뉴의 영속성을 관리하는 MenuPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2023/01/09
 * @version 1.0.0
 **/
@Component
class MenuPersistenceAdapter(
    private val menuMapper: MenuMapper,
    private val queryFactory: JPAQueryFactory
) : QueryMenuPort {

    override fun queryMenusByDate(date: LocalDate): List<Menu> {
        return queryFactory.selectFrom(menu)
            .where(menu.menuId.date.eq(date))
            .fetch()
            .map(menuMapper::toDomainNotNull)
    }

}