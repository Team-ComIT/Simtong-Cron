package team.comit.simtong.persistence.user.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

/**
 *
 * 사용자의 디바이스 토큰을 관리하는 DeviceTokenJpaEntity
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_device")
class DeviceTokenJpaEntity(
    @Id
    val userId: UUID,

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: UserJpaEntity,

    @Column(nullable = false)
    val token: String
)