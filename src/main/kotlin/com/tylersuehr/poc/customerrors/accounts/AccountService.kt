package com.tylersuehr.poc.customerrors.accounts

/**
 * A domain abstraction for account features.
 * @author Tyler Suehr
 */
interface AccountService {
    fun login(credentials: Credentials): Account
    fun create(credentials: Credentials): Account
}
