package com.tylersuehr.poc.customerrors.errors

import java.util.*

/**
 * Data transfer object for custom error.
 * @author Tyler Suehr
 */
internal data class ErrorDto(
    val code: Int,
    val error: String,
    val message: String,
    val path: String,
    val timestamp: Date = Date(),
    val customCode: Int = CustomErrorCode.NONE.code,
)
