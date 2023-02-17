package team.comit.simtong.domain.schedule.port.outbound

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * 일정 작업에서 유저에 관한 조회를 요청하는 ScheduleQueryUserPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/31
 * @version 1.0.0
 **/
interface ScheduleQueryUserPort {

    fun queryUsersBySpotId(spotId: UUID): List<User>

}