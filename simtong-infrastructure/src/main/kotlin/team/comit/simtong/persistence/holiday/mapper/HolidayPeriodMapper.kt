package team.comit.simtong.persistence.holiday.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.holiday.entity.HolidayPeriodJpaEntity
import team.comit.simtong.persistence.spot.repository.SpotJpaRepository

/**
 *
 * 휴무일 작성 기간 모델와 도메인 휴무일 작성 기간을 변환하는 HolidayPeriodMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@Component
class HolidayPeriodMapper(
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<HolidayPeriodJpaEntity, HolidayPeriod> {

    override fun toEntity(model: HolidayPeriod): HolidayPeriodJpaEntity {
        return model.let {
            HolidayPeriodJpaEntity(
                id = HolidayPeriodJpaEntity.Id(
                    spotId = it.spotId,
                    year = it.year,
                    month = it.month
                ),
                startAt = it.startAt,
                endAt = it.endAt,
                spot = spotJpaRepository.findByIdOrNull(it.spotId)!!
            )
        }
    }

    override fun toDomain(entity: HolidayPeriodJpaEntity?): HolidayPeriod? {
        return entity?.let {
            HolidayPeriod(
                year = it.id.year,
                month = it.id.month,
                startAt = it.startAt,
                endAt = it.endAt,
                spotId = it.spot.id!!
            )
        }
    }

    override fun toDomainNotNull(entity: HolidayPeriodJpaEntity): HolidayPeriod {
        return entity.let {
            HolidayPeriod(
                year = it.id.year,
                month = it.id.month,
                startAt = it.startAt,
                endAt = it.endAt,
                spotId = it.spot.id!!
            )
        }
    }
}