package com.example.quartzDemo.Jobs;

import com.example.quartzDemo.info.ArrayInfo;
import com.example.quartzDemo.info.TimerInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ArrayJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(ArrayJob.class);

    private double percentage;

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        ArrayInfo info = (ArrayInfo) jobDataMap.get(ArrayJob.class.getSimpleName());
        if(info.getRemainingInsert()%100==0) LOG.info("Inserted array percentile count is '{}%'", (1000-info.getRemainingInsert())/100);
        this.setPercentage((1000-info.getRemainingInsert())/100);
    }
}
