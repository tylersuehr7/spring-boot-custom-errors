package com.tylersuehr.poc.customerrors.errors

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.handler.ResponseStatusExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * A custom error handler for all exceptions.
 * @author Tyler Suehr
 */
@RestController
@ControllerAdvice
internal class ErrorController : ResponseEntityExceptionHandler() {
}
