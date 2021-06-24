package com.tylersuehr.poc.customerrors

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main application entry-point.
 * @author Tyler Suehr
 */
@SpringBootApplication
class ApiCustomErrorApplication

fun main(args: Array<String>) {
    runApplication<ApiCustomErrorApplication>(*args)
}
