package team.comit.simtong.domain.holiday.job

import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.domain.holiday.port.outbound.HolidayPeriodQueryUserPort
import team.comit.simtong.domain.holiday.port.outbound.HolidayPeriodSendNotificationPort
import team.comit.simtong.domain.holiday.port.outbound.QueryHolidayPeriodPort
import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.ReadOnlyJob
import java.time.LocalDate

/**
 *
 * 휴무일 작성 기간 마감 1시간 전 공지를 담당하는 NoticeHolidayPeriodDeadlineBeforeOneHourJob
 *
 * @author Chokyunghyeon
 * @date 2023/02/11
 * @version 1.0.0
 **/
@ReadOnlyJob
class NoticeHolidayPeriodDeadlineBeforeOneHourJob(
    private val queryHolidayPeriodPort: QueryHolidayPeriodPort,
    private val queryUserPort: HolidayPeriodQueryUserPort,
    private val sendNotificationPort: HolidayPeriodSendNotificationPort
) {

    fun execute() {
        val holidayPeriods: List<HolidayPeriod> = queryHolidayPeriodPort.queryHolidayPeriodsByEndAt(LocalDate.now())

        holidayPeriods.forEach {
            val users: List<User> = queryUserPort.queryUsersBySpotId(it.spotId)

            sendNotificationPort.sendMulticastMessage(
                title = "휴무표 작성 마감 1시간 전이에요!",
                content = "오늘은 ${it.month}월 휴무표 작성 마감 1시간 전 입니다. 아직 휴무표를 작성하지 않으셨다면 서둘러 휴무표를 작성해 주세요.",
                type = NotificationType.HOLIDAY,
                userIds = users.map(User::id),
                identify = null
            )
        }
    }

}