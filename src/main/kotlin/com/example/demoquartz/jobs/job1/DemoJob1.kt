package com.example.demoquartz.jobs.job1

import org.quartz.DisallowConcurrentExecution
import org.quartz.Job
import org.quartz.JobExecutionContext

@DisallowConcurrentExecution
class DemoJob1(private val service: DemoService1) : Job {
    override fun execute(context: JobExecutionContext?) {
        service.callJob()
    }
}
