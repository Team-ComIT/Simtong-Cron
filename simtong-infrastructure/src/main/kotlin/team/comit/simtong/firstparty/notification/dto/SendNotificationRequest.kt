package team.comit.simtong.firstparty.notification.dto

import team.comit.simtong.domain.notification.NotificationType
import java.util.UUID

/**
 *
 * 알림 전송 요청을 하는 SendNotificationRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
data class SendNotificationRequest(
    val title: String,

    val content: String,

    val type: NotificationType,

    val identify: UUID?,

    val userId: UUID
)