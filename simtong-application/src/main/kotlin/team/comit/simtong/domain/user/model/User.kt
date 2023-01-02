package team.comit.simtong.domain.user.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * 유저 애그리거트 루트를 담당하는 User
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Aggregate
data class User(
    val id: UUID,

    val name: String,

    val nickname: String,

    val authority: Authority,

    val email: String,

    val employeeNumber: Int,

    val password: String,

    val profileImagePath: String,

    val spotId: UUID,

    val teamId: UUID
) {

    /**
     *
     * 유저의 권한을 나타내는 User Authority
     *
     * @author Chokyunghyeon
     * @date 2022/12/28
     * @version 1.0.0
     **/
    enum class Authority {

        /**
         * 일반 유저
         */
        ROLE_COMMON,

        /**
         * 관리자
         */
        ROLE_ADMIN,

        /**
         * 최고 관리자
         */
        ROLE_SUPER
    }
}