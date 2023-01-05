package team.comit.simtong.persistence.user.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.beans.factory.annotation.Autowired
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.spot.repository.SpotJpaRepository
import team.comit.simtong.persistence.team.repository.TeamJpaRepository
import team.comit.simtong.persistence.user.entity.UserJpaEntity

/**
 *
 * 유저 엔티티와 유저 애그리거트 변환을 담당하는 UserMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/31
 * @version 1.0.0
 **/
@Mapper
abstract class UserMapper : GenericMapper<UserJpaEntity, User> {

    @Autowired
    protected lateinit var teamJpaRepository: TeamJpaRepository

    @Autowired
    protected lateinit var spotJpaRepository: SpotJpaRepository

    @Mappings(
        Mapping(target = "spotId", expression = "java(entity.getSpot().getId())"),
        Mapping(target = "teamId", expression = "java(entity.getTeam().getId())")
    )
    abstract override fun toDomain(entity: UserJpaEntity?): User?

    @Mappings(
        Mapping(target = "spotId", expression = "java(entity.getSpot().getId())"),
        Mapping(target = "teamId", expression = "java(entity.getTeam().getId())")
    )
    abstract override fun toDomainNotNull(entity: UserJpaEntity): User

    @Mappings(
        Mapping(target = "spot", expression = "java(spotJpaRepository.findById(model.getSpotId()).orElse(null))"),
        Mapping(target = "team", expression = "java(teamJpaRepository.findById(model.getTeamId()).orElse(null))")
    )
    abstract override fun toEntity(model: User): UserJpaEntity

}