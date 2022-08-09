package com.example.quartzDemo.timerService;

import com.example.quartzDemo.info.ArrayInfo;
import com.example.quartzDemo.util.ArrayUtils;
import com.example.quartzDemo.util.TimerUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.quartzDemo.info.TimerInfo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
         this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass, final TimerInfo info) {
        final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, info);
        final Trigger trigger = TimerUtils.buildTrigger(jobClass, info);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void schedule(final Class arrayClass, final ArrayInfo arrayInfo) {
        final JobDetail jobDetail = ArrayUtils.buildJobDetail(arrayClass, arrayInfo);
        final Trigger trigger = ArrayUtils.buildTrigger(arrayClass, arrayInfo);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<TimerInfo> getAllRunningTimers() {
        try {
            return scheduler.getJobKeys(GroupMatcher.anyGroup())
                    .stream()
                    .map(jobKey -> {
                        try {
                            final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                            return (TimerInfo) jobDetail.getJobDataMap().get(jobKey.getName());
                        } catch (SchedulerException e) {
                            LOG.error(e.getMessage(), e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public TimerInfo getRunningTimer(String timerID) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey((timerID)));
            if(jobDetail==null) return null;
            return (TimerInfo)  jobDetail.getJobDataMap().get(timerID);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    public void updateTimer(final String timerID, final TimerInfo info) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey((timerID)));
            if(jobDetail==null) {
                LOG.error("Failed to find timer wih Id '{}'", timerID);
            }
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void updateArray(final String timerID, final ArrayInfo info) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey((timerID)));
            if(jobDetail==null) {
                LOG.error("Failed to find timer wih Id '{}'", timerID);
            }
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Boolean deleteTimer (final String timerId) {
        try {
            return scheduler.deleteJob(new JobKey(timerId));
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
            scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener(this));
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }


}
