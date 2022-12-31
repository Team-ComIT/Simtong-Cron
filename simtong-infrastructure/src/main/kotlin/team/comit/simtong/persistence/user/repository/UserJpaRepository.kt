package team.comit.simtong.persistence.user.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.util.UUID

/**
 *
 * 유저의 Spring Repository를 관리하는 UserJpaRepository
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Repository
interface UserJpaRepository : CrudRepository<UserJpaEntity, UUID> {

    fun queryUserJpaEntitiesBySpotId(spotId: UUID) : List<UserJpaEntity>
}