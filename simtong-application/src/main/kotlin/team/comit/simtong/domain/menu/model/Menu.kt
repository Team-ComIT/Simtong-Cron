package team.comit.simtong.domain.menu.model

import java.time.LocalDate
import java.util.UUID

/**
 *
 * 메뉴 Aggregate Root를 담당하는 Menu
 *
 * @author Chokyunghyeon
 * @date 2023/01/09
 * @version 1.0.0
 **/
data class Menu(
    val date: LocalDate,

    val spotId: UUID,

    val meal: String
)