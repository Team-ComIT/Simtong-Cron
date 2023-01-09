package team.comit.simtong.domain.menu.outbound.port

import team.comit.simtong.domain.notification.NotificationType
import java.util.UUID

/**
 *
 * 메뉴 Domain에서 알림을 전송 요청하는 MenuSendNotificationPort
 *
 * @author Chokyunghyeon
 * @date 2023/01/09
 * @version 1.0.0
 **/
interface MenuSendNotificationPort {

    fun sendMulticastMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID?,
        userIds: List<UUID>
    )
}