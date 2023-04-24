package com.example.demoquartz.jobs

import com.example.demoquartz.jobs.job1.DemoJob1
import com.example.demoquartz.jobs.job1.DemoService1
import com.example.demoquartz.jobs.job2.DemoJob2
import com.example.demoquartz.jobs.job2.DemoService2
import com.example.demoquartz.properties.DemoProperties
import com.example.demoquartz.properties.PREFIX
import org.quartz.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

private inline fun <reified T : Job> newJobDetail(name: String) = JobBuilder
    .newJob()
    .ofType(T::class.java)
    .storeDurably()
    .withIdentity(name)
    .build()

private fun newTrigger(job: JobDetail, name: String, interval: Duration) = TriggerBuilder
    .newTrigger()
    .forJob(job)
    .withIdentity(name)
    .withSchedule(
        SimpleScheduleBuilder
            .simpleSchedule()
            .repeatForever()
            .withIntervalInMilliseconds(interval.toMillis())
    )
    .build()

@Configuration
class JobsConfig {
    @Configuration
    @ConditionalOnProperty("$PREFIX.jobs.job1.enabled", havingValue = "true", matchIfMissing = false)
    class Job1Config {
        @Bean
        fun jobDetail1(): JobDetail {
            return newJobDetail<DemoJob1>("Qrtz_JobDetail1")
        }

        @Bean
        fun demoService1(): DemoService1 {
            return DemoService1()
        }

        @Bean
        fun demoJob1(service: DemoService1): DemoJob1 {
            return DemoJob1(service)
        }

        @Bean
        fun trigger1(@Qualifier("jobDetail1") job: JobDetail, properties: DemoProperties): Trigger {
            return newTrigger(job, "Qrtz_Trigger1", properties.jobs.job1.interval)
        }
    }

    @Configuration
    @ConditionalOnProperty("$PREFIX.jobs.job2.enabled", havingValue = "true", matchIfMissing = false)
    class Job2Config {
        @Bean
        fun jobDetail2(): JobDetail {
            return newJobDetail<DemoJob2>("Qrtz_JobDetail2")
        }

        @Bean
        fun demoService2(): DemoService2 {
            return DemoService2()
        }

        @Bean
        fun demoJob2(service: DemoService2): DemoJob2 {
            return DemoJob2(service)
        }

        @Bean
        fun trigger2(@Qualifier("jobDetail2") job: JobDetail, properties: DemoProperties): Trigger {
            return newTrigger(job, "Qrtz_Trigger2", properties.jobs.job2.interval)
        }
    }
}
