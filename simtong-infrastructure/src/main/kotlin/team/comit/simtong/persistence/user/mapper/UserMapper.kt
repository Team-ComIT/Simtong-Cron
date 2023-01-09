package team.comit.simtong.persistence.user.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
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
@Component
class UserMapper(
    private val spotJpaRepository: SpotJpaRepository,
    private val teamJpaRepository: TeamJpaRepository
) : GenericMapper<UserJpaEntity, User> {

    override fun toEntity(model: User): UserJpaEntity {
        return model.let {
            UserJpaEntity(
                id = it.id,
                employeeNumber = it.employeeNumber,
                name = it.name,
                nickname = it.nickname,
                password = it.password,
                email = it.email,
                authority = it.authority,
                spot = spotJpaRepository.findByIdOrNull(it.spotId)!!,
                team = teamJpaRepository.findByIdOrNull(it.teamId)!!,
                profileImagePath = it.profileImagePath,
                deletedAt = it.deletedAt
            )
        }
    }

    override fun toDomain(entity: UserJpaEntity?): User? {
        return entity?.let {
            User(
                id = it.id!!,
                employeeNumber = it.employeeNumber,
                name = it.name,
                nickname = it.nickname,
                password = it.password,
                email = it.email,
                authority = it.authority,
                spotId = it.spot.id!!,
                teamId = it.team.id!!,
                profileImagePath = it.profileImagePath,
                deletedAt = it.deletedAt
            )
        }
    }

    override fun toDomainNotNull(entity: UserJpaEntity): User {
        return entity.let {
            User(
                id = it.id!!,
                employeeNumber = it.employeeNumber,
                name = it.name,
                nickname = it.nickname,
                password = it.password,
                email = it.email,
                authority = it.authority,
                spotId = it.spot.id!!,
                teamId = it.team.id!!,
                profileImagePath = it.profileImagePath,
                deletedAt = it.deletedAt
            )
        }
    }

}