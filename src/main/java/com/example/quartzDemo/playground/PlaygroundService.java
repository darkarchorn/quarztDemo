package com.example.quartzDemo.playground;

import com.example.quartzDemo.Jobs.ArrayJob;
import com.example.quartzDemo.info.ArrayInfo;
import com.example.quartzDemo.info.TimerInfo;
import com.example.quartzDemo.timerService.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.quartzDemo.Jobs.HelloWorldJob;

import java.util.List;

@Service
public class PlaygroundService {
    private final SchedulerService scheduler;

    @Autowired
    public PlaygroundService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runHelloWorldJob() {
        final TimerInfo info = new TimerInfo();
        info.setTotalFireCount(5);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(2000L);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("My callback data");
        scheduler.schedule(HelloWorldJob.class, info);
    }

    public List<TimerInfo> getAllRunningTimers() {
        return scheduler.getAllRunningTimers();
    }

    public List<ArrayInfo> getAllRunningArrays() {
        return scheduler.getAllRunningArrays();
    }

    public TimerInfo getRunningTimer(String timerId) {
        return scheduler.getRunningTimer(timerId);
    }

    public ArrayInfo getRunningArray(String arrayId) {
        return scheduler.getRunningArray(arrayId);
    }

    public Boolean deleteTimer(final String timerId) {
        return scheduler.deleteTimer(timerId);
    }

    public void runArrayJob() {
        final ArrayInfo info = new ArrayInfo();
        info.setAimInsertion(1000);
        info.setInserted(1000);
        info.setRemainingInsert(info.getInserted());
        info.setRepeatIntervalMs(1L);
        info.setInitialOffsetMs(1000);
        scheduler.schedule(ArrayJob.class, info);
    }
}
