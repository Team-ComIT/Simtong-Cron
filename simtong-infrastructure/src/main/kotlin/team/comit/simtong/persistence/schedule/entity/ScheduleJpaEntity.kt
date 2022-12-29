package team.comit.simtong.persistence.schedule.entity

import org.hibernate.annotations.ColumnDefault
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.persistence.BaseEntity
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 *
 * 일정에 대해 관리하는 ScheduleJpaEntity
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_schedule")
class ScheduleJpaEntity(
    override val id: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: UserJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val title: String,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    @Enumerated(EnumType.STRING)
    val scope: Schedule.Scope,

    @Column(nullable = false)
    val startAt: LocalDate,

    @Column(nullable = false)
    val endAt: LocalDate,

    @Column(nullable = false)
    @ColumnDefault("'08:30:00'")
    val alarmTime: LocalTime
) : BaseEntity(id)