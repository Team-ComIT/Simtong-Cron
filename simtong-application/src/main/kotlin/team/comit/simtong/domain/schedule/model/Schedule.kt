package team.comit.simtong.domain.schedule.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/**
 *
 * 일정 애그리거트의 루트를 담당하는 Schedule
 *
 * @author Chokyunghyeon
 * @date 2022/12/26
 * @version 1.0.0
 **/
@Aggregate
data class Schedule(
    val id: UUID,

    val userId: UUID,

    val spotId: UUID,

    val title: String,

    val scope: Scope,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val alarmTime: LocalTime
) {

    /**
     *
     * 일정의 범위를 구분하는 Schedule Scope
     *
     * @author Chokyunghyeon
     * @date 2022/12/28
     * @version 1.0.0
     **/
    enum class Scope {

        /**
         * 개인 일정
         */
        INDIVIDUAL,

        /**
         * 전체 일정
         */
        ENTIRE
    }
}