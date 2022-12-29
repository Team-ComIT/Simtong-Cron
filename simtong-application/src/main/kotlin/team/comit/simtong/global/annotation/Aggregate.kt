package team.comit.simtong.global.annotation

/**
 *
 * 모델의 일관성을 관리하는 기준이 되는 Aggregate
 *
 * @author Chokyunghyeon
 * @date 2022/12/28
 * @version 1.0.0
 **/
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Aggregate
