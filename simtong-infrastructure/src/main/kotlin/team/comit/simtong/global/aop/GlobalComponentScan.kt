package team.comit.simtong.global.aop

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import team.comit.simtong.global.annotation.Job
import team.comit.simtong.global.annotation.ReadOnlyJob

/**
 *
 * 어노테이션을 스캔해 빈으로 등록하는 GlobalComponentScan
 *
 * @author Chokyunghyeon
 * @date 2022/12/27
 * @version 1.0.0
 **/
@Configuration
@ComponentScan(
    basePackages = ["team.comit.simtong"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                Job::class,
                ReadOnlyJob::class
            ]
        )
    ]
)
class GlobalComponentScan