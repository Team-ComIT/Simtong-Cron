package team.comit.simtong.scheduler

/**
 *
 * Cron 표현식을 관리하는 CronExpressions
 *
 * @author Chokyunghyeon
 * @date 2022/12/27
 * @version 1.0.0
 **/
object CronExpressions {

    /**
     * 매일 0시
     */
    const val EVERYDAY_0 = "0 0 0 * * *"

    /**
     * 매일 23시 00분
     */
    const val EVERYDAY_23 = "0 0 23 * * *"

    /**
     * 매일 20시 30분
     */
    const val EVERYDAY_20_30 = "0 30 20 * * *"

    /**
     * 매일 12시 30분
     */
    const val EVERYDAY_12_30 = "0 30 12 * * *"

    /**
     * 매일 12시 00분
     */
    const val EVERYDAY_12 = "0 0 12 * * *"

    /**
     * 항상 (1분마다)
     */
    const val ALWAYS = "* * * * * *"

}