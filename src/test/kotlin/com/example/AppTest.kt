package com.example
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

@MicronautTest
class AppTest {

    @Test
    fun testItWorks(application: EmbeddedApplication<*>) {
        assertTrue(application.isRunning)
    }

}
