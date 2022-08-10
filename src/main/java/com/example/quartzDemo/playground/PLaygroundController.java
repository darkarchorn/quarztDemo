package com.example.quartzDemo.playground;

import com.example.quartzDemo.Jobs.ArrayJob;
import com.example.quartzDemo.info.ArrayInfo;
import com.example.quartzDemo.info.TimerInfo;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class PLaygroundController {
    private final PlaygroundService service;
    int rmi = 0;
    int aim = 0;

    @Autowired
    public PLaygroundController(final PlaygroundService service) {
        this.service = service;
    }

    @PostMapping("/runhelloworld")
    public void runHelloWorldJob() {
        service.runHelloWorldJob();
    }

    @PostMapping("/runArrayJob")
    public void runArrayJob() {
        service.runArrayJob();
    }

    @GetMapping
    public String getPercentageOfWorkDone() {
        List<ArrayInfo> list = service.getAllRunningArrays();
        for(ArrayInfo ai : list) {
            aim += ai.getAimInsertion();
            rmi += ai.getRemainingInsert();
        }
        return "Work completed: " + ((aim-rmi)/100) + "%";
    }


    /*@GetMapping
    public List<TimerInfo> getAllRunningTimers() {
        return service.getAllRunningTimers();
    }*/

    @GetMapping("/{timerID}")
    public TimerInfo getRunningTimer(@PathVariable String timerID) {
        return service.getRunningTimer(timerID);
    }

    @DeleteMapping("/{timerId}")
    public Boolean deleteTimer(@PathVariable String timerId) {
        return service.deleteTimer(timerId);
    }

}
