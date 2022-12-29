package team.comit.simtong.domain.schedule.job

import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleNotificationPort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryDeviceTokenPort
import team.comit.simtong.domain.user.model.DeviceToken
import team.comit.simtong.global.annotation.ReadOnlyJob
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 다음 날의 일정을 미리 공지하는 작업인 NoticeTomorrowScheduleJob
 *
 * @author Chokyunghyeon
 * @date 2022/12/27
 * @version 1.0.0
 **/
@ReadOnlyJob
class NoticeTomorrowScheduleJob(
    private val querySchedulePort: QuerySchedulePort,
    private val queryDeviceTokenPort: ScheduleQueryDeviceTokenPort,
    private val notificationPort: ScheduleNotificationPort
) {

    fun execute() {
        val tomorrow: LocalDate = LocalDate.now().plusDays(1)

        val schedules: List<Schedule> = querySchedulePort.queryScheduleByDate(tomorrow)

        val individualScheduleMap: Map<UUID, List<Schedule>> = schedules
            .filter { it.scope == Schedule.Scope.INDIVIDUAL }
            .groupBy(Schedule::userId)

        val entireScheduleMap: Map<UUID, List<Schedule>> = schedules
            .filter { it.scope == Schedule.Scope.ENTIRE }
            .groupBy(Schedule::spotId)

        individualScheduleMap.forEach { (userId: UUID, schedules: List<Schedule>) ->
            val deviceToken = queryDeviceTokenPort.queryDeviceTokenByUserId(userId)

            deviceToken?.let {
                val firstSchedule = schedules.first()

                val message = when (schedules.size) {
                    1 -> "내일 ${tomorrow.month}월 ${tomorrow.dayOfMonth}일 \"${firstSchedule.title}\" 개인 일정이 있습니다."

                    else -> "내일 ${tomorrow.month}월 ${tomorrow.dayOfMonth}일 \"${firstSchedule.title}\" 외 ${schedules.size - 1}개의 개인 일정이 있습니다."
                }

                notificationPort.sendMessage(
                    title = "",
                    content = message,
                    type = NotificationType.SCHEDULE,
                    token = it.token,
                    identify = firstSchedule.id
                )
            }
        }

        entireScheduleMap.forEach { (spotId: UUID, schedules: List<Schedule>) ->
            val deviceTokens: List<DeviceToken> = queryDeviceTokenPort
                .querySpotEmployeeDeviceTokensBySpotId(spotId)

            val firstSchedule = schedules.first()

            val message = when (schedules.size) {
                1 -> "내일 ${tomorrow.month}월 ${tomorrow.dayOfMonth}일 \"${firstSchedule.title}\" 지점 일정이 있습니다."

                else -> "내일 ${tomorrow.month}월 ${tomorrow.dayOfMonth}일 \"${firstSchedule.title}\" 외 ${schedules.size - 1}개의 지점 일정이 있습니다."
            }

            notificationPort.sendMulticastMessage(
                title = "",
                content = message,
                type = NotificationType.SCHEDULE,
                identify = firstSchedule.id,
                tokens = deviceTokens.map(DeviceToken::token)
            )
        }
    }

}