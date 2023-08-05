package com.katfun.dddplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DddPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<DddPlaygroundApplication>(*args)
}
