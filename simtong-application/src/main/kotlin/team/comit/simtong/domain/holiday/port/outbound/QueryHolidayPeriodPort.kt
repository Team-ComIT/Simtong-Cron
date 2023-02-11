package team.comit.simtong.domain.holiday.port.outbound

import team.comit.simtong.domain.holiday.model.HolidayPeriod
import java.time.LocalDate

/**
 *
 * 휴무표 작성 기간에 관해 Query를 요청하는 QueryHolidayPeriodPort
 *
 * @author Chokyunghyeon
 * @date 2023/01/02
 * @version 1.0.0
 **/
interface QueryHolidayPeriodPort {

    fun queryHolidayPeriodsByEndAt(endAt: LocalDate): List<HolidayPeriod>
}
