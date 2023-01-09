package team.comit.simtong.domain.menu.outbound.port

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * 메뉴 Domain에서 Menu에 관한 Query를 요청하는 MenuQueryUserPort
 *
 * @author Chokyunghyeon
 * @date 2023/01/09
 * @version 1.0.0
 **/
interface MenuQueryUserPort {

    fun queryUsersBySpotId(spotId: UUID): List<User>
}