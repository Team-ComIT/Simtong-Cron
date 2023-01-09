package team.comit.simtong.domain.schedule.outbound.port

import team.comit.simtong.domain.notification.NotificationType
import java.util.UUID

/**
 *
 * 일정 작업에서 알림 전송을 요청하는 ScheduleNotificationPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/29
 * @version 1.0.0
 **/
interface ScheduleSendNotificationPort {

    fun sendMessage(
        title: String,
        content: String,
        type: NotificationType,
        identify: UUID?,
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