package team.comit.simtong.outbound.notification.dto

import team.comit.simtong.domain.notification.NotificationType
import java.util.UUID

/**
 *
 * 다중 알림 전송을 요청하는 SendMulticastNotificationRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/29
 * @version 1.0.0
 **/
data class SendMulticastNotificationRequest(
    val title: String,

    val content: String,

    val type: NotificationType,

    val identify: UUID?,

    val userIds: List<UUID>
)
