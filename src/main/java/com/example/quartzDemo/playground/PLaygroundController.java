package com.example.quartzDemo.playground;

import com.example.quartzDemo.info.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class PLaygroundController {
    private final PlaygroundService service;

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
    public List<TimerInfo> getAllRunningTimers() {
        return service.getAllRunningTimers();
    }

    @GetMapping("/{timerID}")
    public TimerInfo getRunningTimer(@PathVariable String timerID) {
        return service.getRunningTimer(timerID);
    }

    @DeleteMapping("/{timerId}")
    public Boolean deleteTimer(@PathVariable String timerId) {
        return service.deleteTimer(timerId);
    }
}
