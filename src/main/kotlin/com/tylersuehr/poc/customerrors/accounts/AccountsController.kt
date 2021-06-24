package com.tylersuehr.poc.customerrors.accounts

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Defines restful endpoints for account features.
 * @author Tyler Suehr
 */
@RestController
@RequestMapping("/v1/accounts")
private class AccountsController(val accountService: AccountService) {
    @PostMapping
    fun createAccount(@RequestBody data: Credentials): Account {
        return accountService.create(data)
    }

    @PostMapping("/login")
    fun loginAccount(@RequestBody data: Credentials): Account {
        return accountService.login(data)
    }
}
