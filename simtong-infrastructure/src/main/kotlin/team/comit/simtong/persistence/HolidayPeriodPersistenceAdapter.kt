package team.comit.simtong.persistence

import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.domain.holiday.outbound.port.QueryHolidayPeriodPort
import team.comit.simtong.persistence.holiday.mapper.HolidayPeriodMapper
import team.comit.simtong.persistence.holiday.repository.HolidayPeriodJpaRepository
import java.time.LocalDate

/**
 *
 * 휴무표 작성 기간의 영속성을 관리하는 HolidayPeriodPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2023/01/03
 * @version 1.0.0
 **/
@Component
class HolidayPeriodPersistenceAdapter(
    private val holidayPeriodJpaRepository: HolidayPeriodJpaRepository,
    private val holidayPeriodMapper: HolidayPeriodMapper
) : QueryHolidayPeriodPort {

    override fun queryHolidayPeriodsByEndAt(endAt: LocalDate): List<HolidayPeriod> {
        return holidayPeriodJpaRepository.queryHolidayPeriodJpaEntitiesByEndAt(endAt)
            .mapNotNull(holidayPeriodMapper::toDomain)
    }

}