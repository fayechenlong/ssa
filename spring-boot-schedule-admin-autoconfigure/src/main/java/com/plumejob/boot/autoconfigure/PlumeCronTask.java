package com.plumejob.boot.autoconfigure;

import org.springframework.scheduling.config.CronTask;

public class PlumeCronTask {

    public static final String RUNNING = "running";
    public static final String SUSPEND = "suspend";

    private CronTask cronTask;
    private String status;

    public PlumeCronTask(CronTask cronTask) {
        this.cronTask = cronTask;
    }

    public PlumeCronTask(CronTask cronTask, String status) {
        this.cronTask = cronTask;
        this.status = status;
    }

    public String getJobName() {
        return cronTask.toString();
    }

    public PlumeCronTask start() {
        this.status  = RUNNING;
        return this;
    }

    public PlumeCronTask suspend() {
        this.status  = SUSPEND;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public boolean isRunning() {
        return RUNNING.equalsIgnoreCase(this.status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CronTask getCronTask() {
        return cronTask;
    }
}
