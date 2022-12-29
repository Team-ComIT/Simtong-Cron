package team.comit.simtong.domain.schedule.job

import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleNotificationPort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryDeviceTokenPort
import team.comit.simtong.domain.user.model.DeviceToken
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
    private val queryDeviceTokenPort: ScheduleQueryDeviceTokenPort,
    private val notificationPort: ScheduleNotificationPort
) {

    fun execute() {
        val today: LocalDate = LocalDate.now()

        val schedules: List<Schedule> = querySchedulePort.queryScheduleByDateAndAlarmTime(today, LocalTime.now())

        val individualSchedules: List<Schedule> = schedules.filter { it.scope == Schedule.Scope.INDIVIDUAL }

        val entireSchedules: List<Schedule> = schedules.filter { it.scope == Schedule.Scope.ENTIRE }

        individualSchedules.forEach { schedule: Schedule ->
            val deviceToken: DeviceToken? = queryDeviceTokenPort.queryDeviceTokenByUserId(schedule.userId)

            deviceToken?.let {
                notificationPort.sendMessage(
                    title = "",
                    content = "오늘 ${today.month}월 ${today.dayOfMonth}일 \"${schedule.title}\" 개인 일정이 있습니다.",
                    type = NotificationType.SCHEDULE,
                    identify = schedule.id,
                    token = it.token
                )
            }
        }

        entireSchedules.forEach { schedule: Schedule ->
            val deviceToken: List<DeviceToken> = queryDeviceTokenPort
                .querySpotEmployeeDeviceTokensBySpotId(schedule.userId)

            notificationPort.sendMulticastMessage(
                title = "",
                content = "오늘 ${today.month}월 ${today.dayOfMonth}일 \"${schedule.title}\" 지점 일정이 있습니다.",
                type = NotificationType.SCHEDULE,
                identify = schedule.id,
                tokens = deviceToken.map(DeviceToken::token)
            )
        }
    }

}