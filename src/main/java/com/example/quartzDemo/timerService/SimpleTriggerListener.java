package com.example.quartzDemo.timerService;

import com.example.quartzDemo.Thread.MyThread;
import com.example.quartzDemo.info.ArrayInfo;
import com.example.quartzDemo.info.TimerInfo;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

import java.util.ArrayList;
import java.util.List;

public class SimpleTriggerListener implements TriggerListener {
    private final SchedulerService schedulerService;

    public SimpleTriggerListener(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Override
    public String getName() {
        return SimpleTriggerListener.class.getSimpleName();
    }

    /*@Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        final String timerId = trigger.getKey().getName();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        TimerInfo info = (TimerInfo) jobDataMap.get(timerId);

        if(!info.isRunForever()) {
            int remainingFireCount = info.getRemainingFireCount();
            info.setRemainingFireCount(remainingFireCount-1);
            if (remainingFireCount == 0) {
                return;
            }
        }

        schedulerService.updateTimer(timerId, info);
    }*/

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        final String arrayId = trigger.getKey().getName();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        ArrayInfo info = (ArrayInfo) jobDataMap.get(arrayId);

        /*if(!info.isRunForever()) {
            int remainingInsert = info.getRemainingInsert();
            info.setRemainingInsert(remainingInsert-1);
            if (remainingInsert == 0) {
                return;
            }
        }*/
        /*if(!info.isRunForever()) {
                info.insertNumber(info.getRemainingInsert());
                int remainingInsert = info.getRemainingInsert();
                info.setRemainingInsert(remainingInsert - 1);
                if (remainingInsert == 0) {
                    return;
            }
        }*/
        List<MyThread> threads = new ArrayList<>();
        for(Integer i=0; i<10 ; i++) {
            threads.add(new MyThread(i.toString(), info));
        }
        threads.get(0).run();
        threads.get(1).run();
        threads.get(2).run();
        threads.get(3).run();
        threads.get(4).run();
        threads.get(5).run();
        threads.get(6).run();
        threads.get(7).run();
        threads.get(8).run();
        threads.get(9).run();

        schedulerService.updateArray(arrayId, info);
    }



    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {

    }
}
