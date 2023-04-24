package com.example.demoquartz.properties

import org.hibernate.validator.constraints.time.DurationMax
import org.hibernate.validator.constraints.time.DurationMin
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import java.time.Duration

const val PREFIX = "demo"

@ConfigurationProperties(PREFIX, ignoreUnknownFields = false)
@Validated
data class DemoProperties(
    val jobs: JobsProperties,
)

data class JobsProperties(
    val job1: JobProperties,
    val job2: JobProperties,
)

data class JobProperties(
    val enabled: Boolean,
    @field:DurationMin(seconds = 1)
    @field:DurationMax(seconds = 10)
    val interval: Duration,
)
