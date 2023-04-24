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

@Configuration
class JobsConfig {
    @Configuration
    @ConditionalOnProperty("$PREFIX.jobs.job1.enabled", havingValue = "true", matchIfMissing = false)
    class Job1Config {
        @Bean
        fun jobDetail1(): JobDetail {
            return JobBuilder
                .newJob()
                .ofType(DemoJob1::class.java)
                .storeDurably()
                .withIdentity("Qrtz_Job1_Detail")
                .withDescription("Invoke Sample Job service...")
                .build()
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
            return TriggerBuilder
                .newTrigger()
                .forJob(job)
                .withIdentity("Qrtz_Trigger1")
                .withDescription("Sample trigger")
                .withSchedule(SimpleScheduleBuilder
                    .simpleSchedule()
                    .repeatForever()
                    .withIntervalInMilliseconds(properties.jobs.job1.interval.toMillis()))
                .build()
        }
    }

    @Configuration
    @ConditionalOnProperty("$PREFIX.jobs.job2.enabled", havingValue = "true", matchIfMissing = false)
    class Job2Config {
        @Bean
        fun jobDetail2(): JobDetail {
            return JobBuilder
                .newJob()
                .ofType(DemoJob2::class.java)
                .storeDurably()
                .withIdentity("Qrtz_Job2_Detail")
                .withDescription("Invoke Sample Job service...")
                .build()
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
            return TriggerBuilder
                .newTrigger()
                .forJob(job)
                .withIdentity("Qrtz_Trigger2")
                .withDescription("Sample trigger")
                .withSchedule(SimpleScheduleBuilder
                    .simpleSchedule()
                    .repeatForever()
                    .withIntervalInMilliseconds(properties.jobs.job2.interval.toMillis()))
                .build()
        }
    }
}
