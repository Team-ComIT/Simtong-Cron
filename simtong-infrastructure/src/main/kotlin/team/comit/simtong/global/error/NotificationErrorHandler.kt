package team.comit.simtong.global.error

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

/**
 *
 * 알림 작업에서 발생하는 예외를 핸들링하는 NotificationErrorHandler
 *
 * @author Chokyunghyeon
 * @date 2023/01/01
 * @version 1.0.0
 **/
class NotificationErrorHandler : ErrorDecoder {

    override fun decode(methodKey: String?, response: Response?): Exception {
        return FeignException.errorStatus(methodKey, response)
    }

}