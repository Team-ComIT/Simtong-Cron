package team.comit.simtong.domain.holiday.outbound.port

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * 휴무표 작성 기간에서 유저에 관한 Query를 요청하는 HolidayPeriodQueryUserPort
 *
 * @author Chokyunghyeon
 * @date 2023/01/02
 * @version 1.0.0
 **/
interface HolidayPeriodQueryUserPort {

    fun queryUsersBySpotId(spotId: UUID) : List<User>

}