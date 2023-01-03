package team.comit.simtong.domain.holiday.job

import team.comit.simtong.domain.holiday.outbound.port.HolidayPeriodQueryUserPort
import team.comit.simtong.domain.holiday.outbound.port.HolidayPeriodSendNotificationPort
import team.comit.simtong.domain.holiday.outbound.port.QueryHolidayPeriodPort
import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.ReadOnlyJob
import java.time.LocalDate

/**
 *
 * 휴무표 작성 기간의 종료일 공지를 담당하는 NoticeDueDateHolidayPeriodJob
 *
 * @author Chokyunghyeon
 * @date 2023/01/02
 * @version 1.0.0
 **/
@ReadOnlyJob
class NoticeDueDateHolidayPeriodJob(
    private val queryHolidayPeriodPort: QueryHolidayPeriodPort,
    private val sendNotificationPort: HolidayPeriodSendNotificationPort,
    private val queryUserPort: HolidayPeriodQueryUserPort
) {

    fun execute() {
        val holidayPeriods = queryHolidayPeriodPort.queryHolidayPeriodsByEndAt(LocalDate.now())

        holidayPeriods.forEach {
            val users = queryUserPort.queryUsersBySpotId(it.spotId)

            sendNotificationPort.sendMulticastMessage(
                title = "휴무표 작성 마감일이에요!",
                content = "오늘은 ${it.month}월 휴무표 작성 마감일입니다. 아직 휴무표를 작성하지 않으셨다면 서둘러 휴무표를 작성해주세요!",
                type = NotificationType.HOLIDAY,
                userIds = users.map(User::id)
            )
        }
    }

}