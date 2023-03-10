package team.comit.simtong.persistence.holiday.entity

import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

/**
 *
 * 유저의 휴무일을 관리하는 HolidayJpaEntity
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_holiday")
class HolidayJpaEntity(
    @EmbeddedId
    val id: Id,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    val type: Holiday.Type,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: UserJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(9)", nullable = false)
    val status: Holiday.Status
) {
    
    /**
     *
     * 휴무일의 기본키를 구성하는 Holiday Id
     *
     * @author Chokyunghyeon
     * @date 2022/12/02
     * @version 1.0.0
     **/
    @Embeddable
    data class Id(
        @Column(nullable = false)
        val date: LocalDate,

        @Column(columnDefinition = "BINARY(16)", nullable = false)
        val userId: UUID
    ) : Serializable

}