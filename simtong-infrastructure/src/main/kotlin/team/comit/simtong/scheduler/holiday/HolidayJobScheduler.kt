package team.comit.simtong.scheduler.holiday

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import team.comit.simtong.scheduler.CronExpressions

/**
 *
 * 휴무일에 관한 작업을 처리하는 HolidayJobScheduler
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Component
class HolidayJobScheduler {

    @Scheduled(cron = CronExpressions.EVERYDAY_12_30)
    fun noticeDueDateHolidayPeriod() {

    }

    @Scheduled(cron = CronExpressions.EVERYDAY_23)
    fun noticeHolidayPeriodDeadLineBeforeOneHour() {

    }

    @Scheduled(cron = CronExpressions.EVERYDAY_0)
    fun endHolidayPeriodDeadline() {

    }
}