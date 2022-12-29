package team.comit.simtong.domain.schedule.spi

import team.comit.simtong.domain.schedule.model.Schedule
import java.time.LocalDate
import java.time.LocalTime

/**
 *
 * 일정에 대한 조회를 요청하는 QuerySchedulePort
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
interface QuerySchedulePort {

    fun queryScheduleByDate(date: LocalDate) : List<Schedule>

    fun queryScheduleByDateAndAlarmTime(date: LocalDate, alarmTime: LocalTime) : List<Schedule>

}