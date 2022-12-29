package team.comit.simtong.domain.schedule.spi

import team.comit.simtong.domain.user.model.DeviceToken
import java.util.UUID

/**
 *
 * 일정 작업에서 디바이스 토큰에 관한 조회를 요청하는 ScheduleQueryDeviceTokenPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
interface ScheduleQueryDeviceTokenPort {

    fun queryDeviceTokenByUserId(userId: UUID): DeviceToken?

    fun querySpotEmployeeDeviceTokensBySpotId(spotId: UUID): List<DeviceToken>

}