package com.example.demoquartz

import com.example.demoquartz.properties.DemoProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DemoProperties::class)
class DemoQuartzApplication

fun main(args: Array<String>) {
    runApplication<DemoQuartzApplication>(*args)
}
