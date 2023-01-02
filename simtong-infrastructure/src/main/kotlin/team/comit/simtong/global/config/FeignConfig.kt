package team.comit.simtong.global.config

import feign.codec.ErrorDecoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import team.comit.simtong.global.error.NotificationErrorHandler

/**
 *
 * 외부 API에 관한 설정을 하는 FeignConfig
 *
 * @author Chokyunghyeon
 * @date 2022/12/29
 * @version 1.0.0
 **/
@Configuration
@EnableFeignClients(basePackages = ["team.comit.simtong.firstparty"])
class FeignConfig {

    @Bean
    @ConditionalOnMissingBean(ErrorDecoder::class)
    fun errorDecoder() = NotificationErrorHandler()

}