package team.comit.simtong.persistence.holiday.mapper

import org.springframework.data.repository.findByIdOrNull
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.holiday.entity.HolidayJpaEntity
import team.comit.simtong.persistence.spot.repository.SpotJpaRepository
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * 휴무일 엔티티와 도메인 휴무일 변환을 담당하는 HolidayMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
class HolidayMapper(
    private val spotJpaRepository: SpotJpaRepository,
    private val userJpaRepository: UserJpaRepository
) : GenericMapper<HolidayJpaEntity, Holiday> {


    override fun toEntity(model: Holiday): HolidayJpaEntity {
        return model.let {
            HolidayJpaEntity(
                id = HolidayJpaEntity.Id(
                    date = it.date,
                    userId = it.userId
                ),
                spot = spotJpaRepository.findByIdOrNull(it.spotId)!!,
                user = userJpaRepository.findByIdOrNull(it.userId)!!,
                status = it.status,
                type = it.type
            )
        }
    }

    override fun toDomain(entity: HolidayJpaEntity?): Holiday? {
        return entity?.let {
            Holiday(
                date = it.id.date,
                userId = it.id.userId,
                spotId = it.spot.id!!,
                type = it.type,
                status = it.status
            )
        }
    }

    override fun toDomainNotNull(entity: HolidayJpaEntity): Holiday {
        return entity.let {
            Holiday(
                date = it.id.date,
                userId = it.id.userId,
                spotId = it.spot.id!!,
                type = it.type,
                status = it.status
            )
        }
    }
}