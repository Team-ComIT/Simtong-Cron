package team.comit.simtong.global.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

/**
 *
 * 외부 API에 관한 설정을 하는 FeignConfig
 *
 * @author Chokyunghyeon
 * @date 2022/12/29
 * @version 1.0.0
 **/
@Configuration
@EnableFeignClients(basePackages = ["team.comit.simtong.outbound"])
class FeignConfig {

}