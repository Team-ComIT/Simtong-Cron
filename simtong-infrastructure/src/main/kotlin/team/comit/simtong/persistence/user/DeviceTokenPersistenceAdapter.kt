package team.comit.simtong.persistence.user

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.DeviceToken
import team.comit.simtong.domain.user.spi.DeviceTokenPort
import team.comit.simtong.persistence.user.entity.QDeviceTokenJpaEntity.deviceTokenJpaEntity as deviceToken
import team.comit.simtong.persistence.user.entity.QUserJpaEntity.userJpaEntity as user
import team.comit.simtong.persistence.user.mapper.DeviceTokenMapper
import team.comit.simtong.persistence.user.repository.DeviceTokenJpaRepository
import java.util.UUID

/**
 *
 * 디바이스 토큰의 영속성을 관리하는 DeviceTokenPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Component
class DeviceTokenPersistenceAdapter(
    private val deviceTokenJpaRepository: DeviceTokenJpaRepository,
    private val deviceTokenMapper: DeviceTokenMapper,
    private val queryFactory: JPAQueryFactory
) : DeviceTokenPort {

    override fun queryDeviceTokenByUserId(userId: UUID): DeviceToken? {
        return deviceTokenJpaRepository.findByIdOrNull(userId)
            .let(deviceTokenMapper::toDomain)
    }

    override fun querySpotEmployeeDeviceTokensBySpotId(spotId: UUID): List<DeviceToken> {
        return queryFactory.selectDistinct(deviceToken)
            .from(deviceToken)
            .innerJoin(user)
            .where(
                user.spot.id.eq(spotId)
            )
            .fetch()
            .mapNotNull(deviceTokenMapper::toDomain)
    }

}