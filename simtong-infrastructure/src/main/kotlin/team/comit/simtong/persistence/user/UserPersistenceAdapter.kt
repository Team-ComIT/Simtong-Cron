package team.comit.simtong.persistence.user

import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.port.outbound.HolidayPeriodQueryUserPort
import team.comit.simtong.domain.menu.port.outbound.MenuQueryUserPort
import team.comit.simtong.domain.schedule.port.outbound.ScheduleQueryUserPort
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.persistence.user.mapper.UserMapper
import team.comit.simtong.persistence.user.repository.UserJpaRepository
import java.util.UUID

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
) : ScheduleQueryUserPort, HolidayPeriodQueryUserPort, MenuQueryUserPort {

    override fun queryUsersBySpotId(spotId: UUID): List<User> {
        return userJpaRepository.queryUserJpaEntitiesBySpotId(spotId)
            .map(userMapper::toDomainNotNull)
    }

}