package team.comit.simtong.domain.user.model

import team.comit.simtong.global.annotation.Aggregate
import java.util.UUID

/**
 *
 * 유저 애그리거트의 서브 애그리거트를 담당하는 DeviceToken
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Aggregate
data class DeviceToken(
    val userId: UUID,

    val token: String
)