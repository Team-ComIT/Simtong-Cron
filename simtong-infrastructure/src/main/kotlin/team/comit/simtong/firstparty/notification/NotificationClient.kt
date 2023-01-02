package team.comit.simtong.firstparty.notification

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import team.comit.simtong.firstparty.FeignClientUrls
import team.comit.simtong.firstparty.notification.dto.SendMulticastNotificationRequest
import team.comit.simtong.firstparty.notification.dto.SendNotificationRequest

/**
 *
 * 알림 API에 요청하는 NotificationClient
 *
 * @author Chokyunghyeon
 * @date 2022/12/29
 * @version 1.0.0
 **/
@Component
@FeignClient(
    name = "notification-client",
    url = FeignClientUrls.NOTIFICATION_SERVER,
    path = "/notifications"
)
interface NotificationClient {

    @PostMapping
    fun sendNotification(
        @RequestBody request: SendNotificationRequest
    )

    @PostMapping("/list")
    fun sendMulticastNotification(
        @RequestBody request: SendMulticastNotificationRequest
    )

}