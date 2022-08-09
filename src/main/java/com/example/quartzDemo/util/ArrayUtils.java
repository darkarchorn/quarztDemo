package com.example.quartzDemo.util;

import com.example.quartzDemo.info.ArrayInfo;
import org.quartz.*;

import java.util.Date;

public class ArrayUtils {
    private ArrayUtils() {

    }

    public static JobDetail buildJobDetail(final Class arrayCLass, final ArrayInfo info) {
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(arrayCLass.getSimpleName(), info);
        return JobBuilder
                .newJob(arrayCLass)
                .withIdentity(arrayCLass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class arrayCLass, final ArrayInfo info) {
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(info.getRepeatIntervalMs());

        if(info.isRunForever()) {
            builder = builder.repeatForever();
        } else {
            builder = builder.withRepeatCount(info.getInserted()-1);
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(arrayCLass.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis() + info.getInitialOffsetMs()))
                .build();
    }
}
