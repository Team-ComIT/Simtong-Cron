package team.comit.simtong.outbound

/**
 *
 * 외부 서버 URL을 관리하는 FeignClientUrls
 *
 * @author Chokyunghyeon
 * @date 2023/01/02
 * @version 1.0.0
 **/
object FeignClientUrls {

    const val NOTIFICATION_SERVER = "\${feign.client.url.notification-server}"

}