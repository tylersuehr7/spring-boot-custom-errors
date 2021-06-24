package com.tylersuehr.poc.customerrors.accounts

import com.tylersuehr.poc.customerrors.errors.CustomErrorCode
import com.tylersuehr.poc.customerrors.errors.CustomResponseCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Thrown when a username has already been used.
 * @author Tyler Suehr
 */
@CustomResponseCode(CustomErrorCode.MESSAGE)
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class InvalidUsernameException : Exception("Username already taken!")
