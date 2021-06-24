/*
 * Copyright (c) 2021 Tyler Suehr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
