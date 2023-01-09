package team.comit.simtong.persistence.schedule.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.schedule.entity.ScheduleJpaEntity
import team.comit.simtong.persistence.spot.repository.SpotJpaRepository
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * Schedule Entity와 Schedule Aggregate를 변환하는 ScheduleMapper
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
@Component
class ScheduleMapper(
    private val userJpaRepository: UserJpaRepository,
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<ScheduleJpaEntity, Schedule> {

    override fun toDomain(entity: ScheduleJpaEntity?): Schedule? {
        return entity?.let {
            Schedule(
                id = it.id!!,
                userId = it.user.id!!,
                spotId = it.spot.id!!,
                title = it.title,
                scope = it.scope,
                startAt = it.startAt,
                endAt = it.endAt,
                alarmTime = it.alarmTime
            )
        }
    }

    override fun toDomainNotNull(entity: ScheduleJpaEntity): Schedule {
        return entity.let {
            Schedule(
                id = it.id!!,
                userId = it.user.id!!,
                spotId = it.spot.id!!,
                title = it.title,
                scope = it.scope,
                startAt = it.startAt,
                endAt = it.endAt,
                alarmTime = it.alarmTime
            )
        }
    }

    override fun toEntity(model: Schedule): ScheduleJpaEntity {
        return model.let {
            ScheduleJpaEntity(
                id = it.id,
                user = userJpaRepository.findByIdOrNull(it.userId)!!,
                spot = spotJpaRepository.findByIdOrNull(it.spotId)!!,
                title = it.title,
                scope = it.scope,
                startAt = it.startAt,
                endAt = it.endAt,
                alarmTime = it.alarmTime
            )
        }
    }
}