/*
 * Copyright (c) 2021 Tyler Suehr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.tylersuehr.poc.customerrors.errors

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation


/**
 * A custom error handler for all exceptions.
 * @author Tyler Suehr
 */
@RestController
@ControllerAdvice
internal class ErrorController : ResponseEntityExceptionHandler() {
    /**
     * Handle base exception class.
     * Basically handles any exception that has not been already handled.
     */
    @ExceptionHandler(Exception::class)
    fun handleUnknownException(cause: Exception, request: WebRequest): ResponseEntity<Any> {
        // Parse the http status from exception
        var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        if (cause::class.hasAnnotation<ResponseStatus>()) {
            status = cause::class.findAnnotation<ResponseStatus>()!!.value
        }
        return handleExceptionInternal(cause, null, HttpHeaders.EMPTY, status, request)
    }

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
