package team.comit.simtong.domain.holiday.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무표 작성 기간 애그리거트의 루트를 담당하는 HolidayPeriod
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Aggregate
data class HolidayPeriod(
    val year: Int,

    val month: Int,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val spotId: UUID
)