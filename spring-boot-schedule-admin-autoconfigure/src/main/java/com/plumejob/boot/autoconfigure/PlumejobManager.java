package com.plumejob.boot.autoconfigure;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

@Component
public class PlumejobManager  implements SchedulingConfigurer, InitializingBean {
    @Autowired
    private ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor;

    private ScheduledTaskRegistrar scheduledTaskRegistrar;


    private final ConcurrentHashMap<String, PlumeCronTask> cronTaskConcurrentHashMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ScheduledTask> scheduledTaskConcurrentHashMap = new ConcurrentHashMap<>();

    public List<PlumeJobDTO> listJob() {

        if (scheduledTaskConcurrentHashMap.isEmpty()) {
            Set<ScheduledTask> scheduledTaskSet = scheduledAnnotationBeanPostProcessor.getScheduledTasks();
            for (ScheduledTask scheduledTask : scheduledTaskSet) {
                scheduledTaskConcurrentHashMap.put(scheduledTask.toString(), scheduledTask);
            }
        }

        List<PlumeJobDTO> list = new ArrayList<>();
        for (String key : cronTaskConcurrentHashMap.keySet()) {
            PlumeCronTask plumeCronTask = cronTaskConcurrentHashMap.get(key);
            PlumeJobDTO jobDTO = new PlumeJobDTO();
            CronTask cronTask = plumeCronTask.getCronTask();
            jobDTO.setJobName(key);
            jobDTO.setExpression(cronTask.getExpression());
            jobDTO.setStatus(plumeCronTask.getStatus());
            list.add(jobDTO);
        }


        return list;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        this.scheduledTaskRegistrar = scheduledTaskRegistrar;
        List<CronTask> cronTaskList = scheduledTaskRegistrar.getCronTaskList();
        for (CronTask cronTask : cronTaskList) {
            cronTaskConcurrentHashMap.put(cronTask.toString(), new PlumeCronTask(cronTask).start());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (scheduledTaskConcurrentHashMap.isEmpty()) {
            Set<ScheduledTask> scheduledTaskSet = scheduledAnnotationBeanPostProcessor.getScheduledTasks();
            for (ScheduledTask scheduledTask : scheduledTaskSet) {
                scheduledTaskConcurrentHashMap.put(scheduledTask.toString(), scheduledTask);
            }
        }
    }

    public void start(String jobName) {
        PlumeCronTask plumeCronTask = cronTaskConcurrentHashMap.get(jobName);
        CronTask cronTask = plumeCronTask.getCronTask();
        ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) cronTask.getRunnable();

        reStartTask(runnable, cronTask.getExpression());
    }

    public void update(String jobName, String expression) {
        PlumeCronTask plumeCronTask = cronTaskConcurrentHashMap.get(jobName);

        if (plumeCronTask.isRunning()) {
            ScheduledMethodRunnable runnable = (ScheduledMethodRunnable)scheduledTaskConcurrentHashMap.get(jobName).getTask().getRunnable();
            reStartTask(runnable, expression);
        } else {
            CronTask cronTask = new CronTask(plumeCronTask.getCronTask().getRunnable(), expression);
            cronTaskConcurrentHashMap.put(jobName, new PlumeCronTask(cronTask));
        }
    }

    public void suspend(String jobName) {
        cancelTask(jobName);

        PlumeCronTask plumeCronTask = cronTaskConcurrentHashMap.get(jobName);
        plumeCronTask.suspend();
    }

    private void reStartTask(ScheduledMethodRunnable runnable, String expression ) {

        cancelTask(runnable.toString());

        // must be new instance
        ScheduledTask newScheduledTask = this.scheduledTaskRegistrar.scheduleCronTask(
                new CronTask(new ScheduledMethodRunnable(runnable.getTarget(), runnable.getMethod()), expression)
        );

        CronTask cronTask = new CronTask(runnable, expression);
        cronTaskConcurrentHashMap.put(runnable.toString(), new PlumeCronTask(cronTask).start());

        if (newScheduledTask != null) {
            scheduledTaskConcurrentHashMap.put(runnable.toString(), newScheduledTask);
        }
    }

    private void cancelTask(String jobName) {
        ScheduledTask scheduledTask = scheduledTaskConcurrentHashMap.get(jobName);
        if (scheduledTask != null)
            scheduledTask.cancel();
    }

    public void run(String jobName) {
        ScheduledTask scheduledTask = scheduledTaskConcurrentHashMap.get(jobName);
        scheduledTaskRegistrar.getScheduler().schedule(scheduledTask.getTask().getRunnable(), new Date());
    }
}
