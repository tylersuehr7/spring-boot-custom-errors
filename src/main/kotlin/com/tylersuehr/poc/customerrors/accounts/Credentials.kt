package com.tylersuehr.poc.customerrors.accounts

/**
 * A request model for account credentials.
 * @author Tyler Suehr
 */
data class Credentials(
    val username: String,
    val password: String,
)
