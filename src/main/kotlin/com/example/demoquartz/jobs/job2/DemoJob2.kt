package com.example.demoquartz.jobs.job2

import org.quartz.DisallowConcurrentExecution
import org.quartz.Job
import org.quartz.JobExecutionContext

@DisallowConcurrentExecution
class DemoJob2(private val service: DemoService2) : Job {
    override fun execute(context: JobExecutionContext?) {
        service.callJob()
    }
}
