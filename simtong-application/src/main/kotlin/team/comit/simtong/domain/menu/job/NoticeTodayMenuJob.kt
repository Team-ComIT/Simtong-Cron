package team.comit.simtong.domain.menu.job

import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.outbound.port.MenuQueryUserPort
import team.comit.simtong.domain.menu.outbound.port.MenuSendNotificationPort
import team.comit.simtong.domain.menu.outbound.port.QueryMenuPort
import team.comit.simtong.domain.notification.NotificationType
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.ReadOnlyJob
import java.time.LocalDate

/**
 *
 * 오늘의 메뉴 공지 작업을 담당하는 NoticeTodayMenuJob
 *
 * @author Chokyunghyeon
 * @date 2023/01/09
 * @version 1.0.0
 **/
@ReadOnlyJob
class NoticeTodayMenuJob(
    private val queryMenuPort: QueryMenuPort,
    private val queryUserPort: MenuQueryUserPort,
    private val sendNotificationPort: MenuSendNotificationPort
) {

    fun execute() {
        val menus: List<Menu> = queryMenuPort.queryMenusByDate(LocalDate.now())

        menus.forEach {
            val employees = queryUserPort.queryUsersBySpotId(it.spotId)

            sendNotificationPort.sendMulticastMessage(
                title = "오늘의 점심 메뉴에요!",
                content = "오늘 점심 메뉴는 ${it.meal} 입니다. 즐점하세요 :)",
                type = NotificationType.MEAL,
                userIds = employees.map(User::id),
                identify = null
            )
        }
    }
}