package team.comit.simtong.persistence.team.entity

import team.comit.simtong.persistence.BaseUUIDEntity
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 *
 * 지점에 속한 팀을 관리하는 TeamJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_team")
class TeamJpaEntity(
    override val id: UUID?,

    @Column(length = 8, nullable = false)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity,
) : BaseUUIDEntity(id)