package team.comit.simtong.persistence.user

import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.UserPort
import team.comit.simtong.persistence.user.mapper.UserMapper
import team.comit.simtong.persistence.user.repository.UserJpaRepository
import java.util.*

/**
 *
 * 유저의 영속성을 관리하는 UserPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/12/31
 * @version 1.0.0
 **/
@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper
) : UserPort {

    override fun queryUsersBySpotId(spotId: UUID): List<User> {
        return userJpaRepository.queryUserJpaEntitiesBySpotId(spotId)
            .mapNotNull(userMapper::toDomain)
    }

}