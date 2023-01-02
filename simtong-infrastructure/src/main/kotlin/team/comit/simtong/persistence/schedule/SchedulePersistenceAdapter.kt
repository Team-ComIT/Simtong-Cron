package team.comit.simtong.persistence.schedule

import team.comit.simtong.persistence.schedule.entity.QScheduleJpaEntity.scheduleJpaEntity as schedule
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.outbound.port.QuerySchedulePort
import team.comit.simtong.persistence.schedule.mapper.ScheduleMapper
import java.time.LocalDate
import java.time.LocalTime

/**
 *
 * Schedule의 영속성을 관리하는 SchedulePersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.1
 **/
@Component
class SchedulePersistenceAdapter(
    private val scheduleMapper: ScheduleMapper,
    private val queryFactory: JPAQueryFactory
) : QuerySchedulePort {

    override fun queryScheduleByDate(date: LocalDate): List<Schedule> {
        return queryFactory.selectFrom(schedule)
            .where(
                schedule.startAt.goe(date),
                schedule.endAt.loe(date)
            )
            .fetch()
            .mapNotNull(scheduleMapper::toDomain)
    }

    override fun queryScheduleByDateAndAlarmTime(
        date: LocalDate,
        alarmTime: LocalTime
    ): List<Schedule> {
        return queryFactory.selectFrom(schedule)
            .where(
                schedule.startAt.goe(date),
                schedule.endAt.loe(date),
                schedule.alarmTime.eq(alarmTime)
            )
            .fetch()
            .mapNotNull(scheduleMapper::toDomain)
    }


}