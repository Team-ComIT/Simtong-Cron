package team.comit.simtong.domain.holiday.outbound.port

import team.comit.simtong.domain.notification.NotificationType
import java.util.UUID

/**
 *
 * 휴무표 작성 기간에서 알림 전송을 담당하는 HolidayPeriodSendNotificationPort
 *
 * @author Chokyunghyeon
 * @date 2023/01/02
 * @version 1.0.0
 **/
interface HolidayPeriodSendNotificationPort {

    fun sendMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID? = null,
        userId: UUID
    )

    fun sendMulticastMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID?,
        userIds: List<UUID>
    )

}