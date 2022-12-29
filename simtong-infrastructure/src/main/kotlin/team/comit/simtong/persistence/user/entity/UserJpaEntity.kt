package team.comit.simtong.persistence.user.entity

import org.hibernate.annotations.ColumnDefault
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.persistence.BaseEntity
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import team.comit.simtong.persistence.team.entity.TeamJpaEntity
import java.time.LocalDateTime
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
 * 유저(직원, 관리자) 정보를 관리하는 UserJpaEntity
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_user")
class UserJpaEntity(
    override val id: UUID?,

    @Column(unique = true, nullable = false)
    val employeeNumber: Int,

    @Column(unique = true, nullable = false)
    val email: String,

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    val authority: User.Authority,

    @Column(length = 10, nullable = false)
    val name: String,

    @Column(length = 20, unique = true, nullable = false)
    val nickname: String,

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    val password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", columnDefinition = "BINARY(16)", nullable = false)
    val team: TeamJpaEntity,

    @Column(nullable = false)
    @ColumnDefault("'default image'")
    val profileImagePath: String,

    val deletedAt: LocalDateTime?
) : BaseEntity(id)