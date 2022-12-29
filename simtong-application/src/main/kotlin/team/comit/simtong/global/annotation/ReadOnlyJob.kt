package team.comit.simtong.global.annotation

import org.springframework.transaction.annotation.Transactional

/**
 *
 * 조회 기능을 가진 작업을 나타내는 ReadOnlyJob
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Transactional(readOnly = true)
annotation class ReadOnlyJob
