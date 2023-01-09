package team.comit.simtong.firstparty.notification

import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.outbound.port.HolidayPeriodSendNotificationPort
import team.comit.simtong.domain.menu.outbound.port.MenuSendNotificationPort
import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.schedule.outbound.port.ScheduleSendNotificationPort
import team.comit.simtong.firstparty.notification.dto.SendMulticastNotificationRequest
import team.comit.simtong.firstparty.notification.dto.SendNotificationRequest
import java.util.UUID

/**
 *
 * 알림에 대한 요청을 담당하는 NotificationAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/12/29
 * @version 1.0.0
 **/
@Component
class NotificationAdapter(
    private val notificationClient: NotificationClient
) : ScheduleSendNotificationPort, HolidayPeriodSendNotificationPort, MenuSendNotificationPort {

    override fun sendMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID?,
        userId: UUID
    ) {
        notificationClient.sendNotification(
            SendNotificationRequest(
                title = title,
                content = content,
                type = type,
                identify = identify,
                userId = userId
            )
        )
    }

    override fun sendMulticastMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID?,
        userIds: List<UUID>
    ) {
        notificationClient.sendMulticastNotification(
            SendMulticastNotificationRequest(
                title = title,
                content = content,
                type = type,
                identify = identify,
                userIds = userIds
            )
        )
    }

}