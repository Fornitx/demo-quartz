package com.example.demoquartz.jobs.job1

import io.github.oshai.KotlinLogging

private val logger = KotlinLogging.logger { }

class DemoService1 {
    fun callJob() {
        logger.info { "${this::class.simpleName}.${this::callJob.name} calling" }
    }
}
