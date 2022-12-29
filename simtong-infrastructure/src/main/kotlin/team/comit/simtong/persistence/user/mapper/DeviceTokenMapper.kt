package team.comit.simtong.persistence.user.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.beans.factory.annotation.Autowired
import team.comit.simtong.domain.user.model.DeviceToken
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.user.entity.DeviceTokenJpaEntity
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * 디바이스 토큰 엔티티와 디바이스 토큰 애그리거트를 변환하는 DeviceTokenMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Mapper
abstract class DeviceTokenMapper : GenericMapper<DeviceTokenJpaEntity, DeviceToken> {

    @Autowired
    protected lateinit var userJpaRepository: UserJpaRepository

    @Mapping(target = "userId", expression = "java(entity.getUserId())")
    abstract override fun toDomain(entity: DeviceTokenJpaEntity?): DeviceToken?

    @Mappings(
        Mapping(target = "user", expression = "java(userJpaRepository.findById(model.getUserId()).orElse(null))"),
        Mapping(target = "userId", expression = "java(model.getUserId())")
    )
    abstract override fun toEntity(model: DeviceToken): DeviceTokenJpaEntity
}