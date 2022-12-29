package team.comit.simtong.scheduler.schedule

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.job.NoticeTodayScheduleJob
import team.comit.simtong.domain.schedule.job.NoticeTomorrowScheduleJob
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.scheduler.CronExpressions

/**
 *
 * 일정에 관한 작업을 담당하는 ScheduleNoticeScheduler
 *
 * @author Chokyunghyeon
 * @date 2022/12/27
 * @version 1.0.0
 **/
@Component
class ScheduleJobScheduler(
    private val noticeTomorrowScheduleJob: NoticeTomorrowScheduleJob,
    private val noticeTodayScheduleJob: NoticeTodayScheduleJob
) {

    @Scheduled(cron = CronExpressions.EVERYDAY_20_30)
    fun nextDayScheduleNotification() {
        noticeTomorrowScheduleJob.execute()
    }

    @Scheduled(cron = CronExpressions.ALWAYS)
    fun scheduleAlarmNotification() {
        noticeTodayScheduleJob.execute()
    }
}