package team.comit.simtong.domain.menu.outbound.port

import team.comit.simtong.domain.menu.model.Menu
import java.time.LocalDate

/**
 *
 * 메뉴에 관해 Query를 요청하는 QueryMenuPort
 *
 * @author Chokyunghyeon
 * @date 2023/01/09
 * @version 1.0.0
 **/
interface QueryMenuPort {

    fun queryMenusByDate(date: LocalDate): List<Menu>
}