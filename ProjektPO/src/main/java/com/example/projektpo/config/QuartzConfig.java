package com.example.projektpo.config;

import com.example.projektpo.scheduling.DownloadWarningsJob;
import com.example.projektpo.service.implementation.JobScheduler;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    private final JobScheduler jobScheduler;

    @Autowired
    public QuartzConfig(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(DownloadWarningsJob.class)
                .withIdentity("downloadWarningsJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        int frequencyInMinutes = jobScheduler.getFrequency() / (60 * 1000);

        String cronExpression = generateCronExpression(frequencyInMinutes);

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("downloadWarningsTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }

    private String generateCronExpression(int frequencyInMinutes) {
        if (frequencyInMinutes < 1) {
            frequencyInMinutes = 1;
        }

        if (frequencyInMinutes < 60) {
            return "0 0/" + frequencyInMinutes + " * * * ?";
        } else if (frequencyInMinutes % 60 == 0) {
            int hours = frequencyInMinutes / 60;
            return "0 0 0/" + hours + " * * ?";
        } else {
            return "0 0 * * * ?";
        }
    }
}
