package com.tylersuehr.poc.customerrors.errors

/**
 * All supported custom error codes specified in documentation.
 * @author Tyler Suehr
 */
enum class CustomErrorCode(val code: Int) {
    NONE(0),
    MESSAGE(1),
    JWT_MISSING(2),
    JWT_UNVERIFIED(3),
    JWT_EXPIRED_REFRESH(4),
    JWT_EXPIRED_LOGOUT(5),
}
