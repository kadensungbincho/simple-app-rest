package simple.app.rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleAppRestApplication

fun main(args: Array<String>) {
    runApplication<SimpleAppRestApplication>(*args)
}
