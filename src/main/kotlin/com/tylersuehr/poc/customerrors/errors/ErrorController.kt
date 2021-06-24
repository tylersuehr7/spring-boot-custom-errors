package com.tylersuehr.poc.customerrors.errors

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation


/**
 * A custom error handler for all exceptions.
 * @author Tyler Suehr
 */
@RestController
@ControllerAdvice
internal class ErrorController : ResponseEntityExceptionHandler() {
    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        // Parse the custom error from exception
        var customCode: CustomErrorCode = CustomErrorCode.NONE
        if (ex::class.hasAnnotation<CustomResponseCode>()) {
            customCode = ex::class.findAnnotation<CustomResponseCode>()!!.code
        }

        // Create the data transfer object for error
        val dto = ErrorDto(
            path = (request as ServletWebRequest).request.requestURI,
            code = status.value(),
            error = status.reasonPhrase,
            message = if (ex.localizedMessage == null || ex.localizedMessage.isEmpty()) "Something went wrong!" else ex.localizedMessage,
            customCode = customCode.code
        )

        return ResponseEntity(dto, status)
    }
}
