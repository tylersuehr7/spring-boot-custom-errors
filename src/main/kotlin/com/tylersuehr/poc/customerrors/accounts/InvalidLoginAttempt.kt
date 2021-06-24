package com.tylersuehr.poc.customerrors.accounts

import com.tylersuehr.poc.customerrors.errors.CustomErrorCode
import com.tylersuehr.poc.customerrors.errors.CustomResponseCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Thrown when a login attempt yield invalid.
 * @author Tyler Suehr
 */
@CustomResponseCode(CustomErrorCode.MESSAGE)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
class InvalidLoginAttempt : Exception("Invalid login attempt!")
