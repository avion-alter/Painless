package com.ucsf.painless.FCMNotifications;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
       // TODO(developer): add long running task here.
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

}