
package team.comit.simtong.domain.holiday.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무일 애그리거트의 루트를 담당하는 Holiday
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
@Aggregate
data class Holiday(
    val date: LocalDate,

    val userId: UUID,

    val type: Type,

    val spotId: UUID,

    val status: Status
) {

    /**
     *
     * 휴무일 상태를 관리하는 Holiday Status
     *
     * @author Chokyunghyeon
     * @date 2022/12/20
     * @version 1.0.0
     **/
    enum class Status {

        /**
         * 휴무표 작성 완료
         */
        WRITTEN,

        /**
         *휴무표 확정 완료
         */
        COMPLETED

    }

    /**
     *
     * 휴무일 유형를 관리하는 Holiday Type
     *
     * @author Chokyunghyeon
     * @date 2022/12/02
     * @version 1.0.0
     **/
    enum class Type {

        /**
         * 휴무일
         */
        HOLIDAY,

        /**
         * 연차
         */
        ANNUAL
    }

}