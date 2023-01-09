package team.comit.simtong.scheduler.menu

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import team.comit.simtong.domain.menu.job.NoticeTodayMenuJob
import team.comit.simtong.scheduler.CronExpressions

/**
 *
 * 메뉴에 관한 작업을 처리하는 MenuJobScheduler
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Component
class MenuJobScheduler(
    private val noticeTodayMenuJob: NoticeTodayMenuJob
) {

    @Scheduled(cron = CronExpressions.EVERYDAY_12)
    fun noticeLunch() {
        noticeTodayMenuJob.execute()
    }

}