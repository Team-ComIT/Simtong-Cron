package team.comit.simtong.domain.schedule.job

import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.port.outbound.QuerySchedulePort
import team.comit.simtong.domain.schedule.port.outbound.ScheduleSendNotificationPort
import team.comit.simtong.domain.schedule.port.outbound.ScheduleQueryUserPort
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

        val (individualSchedules, entireSchedules) = schedules
            .partition { it.scope == Schedule.Scope.INDIVIDUAL }

        individualSchedules.forEach { schedule: Schedule ->
            sendNotificationPort.sendMessage(
                title = "오늘의 일정이에요!",
                content = "오늘 ${today.month}월 ${today.dayOfMonth}일 \"${schedule.title}\" 개인 일정이 있습니다.",
                type = NotificationType.SCHEDULE,
                userId = schedule.userId,
                identify = null
            )
        }

        entireSchedules.forEach { schedule: Schedule ->
            val employees: List<User> = queryUserPort.queryUsersBySpotId(schedule.spotId)

            sendNotificationPort.sendMulticastMessage(
                title = "오늘의 전체 일정이에요!",
                content = "오늘 ${today.month}월 ${today.dayOfMonth}일 \"${schedule.title}\" 지점 일정이 있습니다.",
                type = NotificationType.SCHEDULE,
                identify = null,
                userIds = employees.map(User::id)
            )
        }
    }

}