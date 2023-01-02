package team.comit.simtong.domain.schedule.job

import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.outbound.port.QuerySchedulePort
import team.comit.simtong.domain.schedule.outbound.port.ScheduleSendNotificationPort
import team.comit.simtong.domain.schedule.outbound.port.ScheduleQueryUserPort
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.ReadOnlyJob
import java.time.LocalDate
import java.time.LocalTime

/**
 *
 * 당일 일정 공지 작업을 담당하는 NoticeTodayScheduleJob
 *
 * @author Chokyunghyeon
 * @date 2022/12/29
 * @version 1.0.0
 **/
@ReadOnlyJob
class NoticeTodayScheduleJob(
    private val querySchedulePort: QuerySchedulePort,
    private val queryUserPort: ScheduleQueryUserPort,
    private val sendNotificationPort: ScheduleSendNotificationPort
) {

    fun execute() {
        val today: LocalDate = LocalDate.now()

        val schedules: List<Schedule> = querySchedulePort.queryScheduleByDateAndAlarmTime(today, LocalTime.now())

        val individualSchedules: List<Schedule> = schedules.filter { it.scope == Schedule.Scope.INDIVIDUAL }

        val entireSchedules: List<Schedule> = schedules.filter { it.scope == Schedule.Scope.ENTIRE }

        individualSchedules.forEach { schedule: Schedule ->
            sendNotificationPort.sendMessage(
                title = "",
                content = "오늘 ${today.month}월 ${today.dayOfMonth}일 \"${schedule.title}\" 개인 일정이 있습니다.",
                type = NotificationType.SCHEDULE,
                userId = schedule.userId
            )
        }

        entireSchedules.forEach { schedule: Schedule ->
            val employees: List<User> = queryUserPort.queryUsersBySpotId(schedule.spotId)

            sendNotificationPort.sendMulticastMessage(
                title = "",
                content = "오늘 ${today.month}월 ${today.dayOfMonth}일 \"${schedule.title}\" 지점 일정이 있습니다.",
                type = NotificationType.SCHEDULE,
                identify = schedule.id,
                userIds = employees.map(User::id)
            )
        }
    }

}