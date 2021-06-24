package com.tylersuehr.poc.customerrors.accounts

import org.springframework.stereotype.Service
import java.util.*

/**
 * @author Tyler Suehr
 */
@Service
private class AccountServiceMock : AccountService {
    companion object {
        val mockAccount = Account(id = "0011223344", username = "abc", password = "abc")
        val data = mutableMapOf<String,Account>(Pair(mockAccount.username, mockAccount))
    }

    override fun login(credentials: Credentials): Account {
        val found = data[credentials.username]
        if (found == null || found.password != credentials.password) {
            throw InvalidLoginAttempt()
        }
        return found
    }

    override fun create(credentials: Credentials): Account {
        val (username,password) = credentials
        if (data.containsKey(username)) {
            throw InvalidUsernameException()
        }
        val account = Account(UUID.randomUUID().toString(), username, password)
        data[username] = account
        return account
    }
}
