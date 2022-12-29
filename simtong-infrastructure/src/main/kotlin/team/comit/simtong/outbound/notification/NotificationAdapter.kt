package team.comit.simtong.outbound.notification

import org.springframework.stereotype.Component
import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.notification.spi.NotificationPort
import team.comit.simtong.outbound.notification.dto.SendMulticastNotificationRequest
import team.comit.simtong.outbound.notification.dto.SendNotificationRequest
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
) : NotificationPort {

    override fun sendMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID,
        token: String
    ) {
        notificationClient.sendNotification(
            SendNotificationRequest(
                title = title,
                content = content,
                type = type,
                identify = identify,
                token = token
            )
        )
    }

    override fun sendMulticastMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID,
        tokens: List<String>
    ) {
        notificationClient.sendMulticastNotification(
            SendMulticastNotificationRequest(
                title = title,
                content = content,
                type = type,
                identify = identify,
                tokens = tokens
            )
        )
    }

}