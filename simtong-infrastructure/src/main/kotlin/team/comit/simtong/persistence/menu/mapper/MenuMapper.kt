package team.comit.simtong.persistence.menu.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.menu.entity.MenuJpaEntity
import team.comit.simtong.persistence.spot.repository.SpotJpaRepository

/**
 *
 * 메뉴 엔티티와 도메인 모델을 변환하는 MenuMapper
 *
 * @author Chokyunghyeon
 * @date 2023/01/09
 * @version 1.0.0
 **/
@Component
class MenuMapper(
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<MenuJpaEntity, Menu> {

    override fun toEntity(model: Menu): MenuJpaEntity {
        return model.let {
            MenuJpaEntity(
                menuId = MenuJpaEntity.Id(
                    spotId = it.spotId,
                    date = it.date
                ),
                spot = spotJpaRepository.findByIdOrNull(it.spotId)!!,
                meal = it.meal
            )
        }
    }

    override fun toDomain(entity: MenuJpaEntity?): Menu? {
        return entity?.let {
            Menu(
                date = it.menuId.date,
                spotId = it.menuId.spotId,
                meal = it.meal
            )
        }
    }

    override fun toDomainNotNull(entity: MenuJpaEntity): Menu {
        return entity.let {
            Menu(
                date = it.menuId.date,
                spotId = it.menuId.spotId,
                meal = it.meal
            )
        }
    }
}