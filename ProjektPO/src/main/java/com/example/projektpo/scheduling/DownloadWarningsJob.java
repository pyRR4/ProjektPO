package com.example.projektpo.scheduling;

import com.example.projektpo.service.implementation.JobScheduler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DownloadWarningsJob implements Job {

    private final JobScheduler jobScheduler;

    @Autowired
    public DownloadWarningsJob(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        jobScheduler.downloadWarnings();
    }
}
