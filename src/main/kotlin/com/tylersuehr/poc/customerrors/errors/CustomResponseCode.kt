package com.tylersuehr.poc.customerrors.errors

/**
 * Annotation denoting an exception with custom error code.
 * @author Tyler Suehr
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class CustomResponseCode(val code: CustomErrorCode = CustomErrorCode.NONE)
