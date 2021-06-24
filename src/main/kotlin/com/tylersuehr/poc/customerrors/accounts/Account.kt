package com.tylersuehr.poc.customerrors.accounts

/**
 * Data model for an authenticated account.
 * @author Tyler Suehr
 */
data class Account(
    var id: String,
    var username: String,
    var password: String,
)
