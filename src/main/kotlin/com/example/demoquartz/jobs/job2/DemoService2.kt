package com.example.demoquartz.jobs.job2

import io.github.oshai.KotlinLogging

private val logger = KotlinLogging.logger { }

class DemoService2 {
    fun callJob() {
        logger.info { "${this::class.simpleName}.${this::callJob.name} calling" }
    }
}
