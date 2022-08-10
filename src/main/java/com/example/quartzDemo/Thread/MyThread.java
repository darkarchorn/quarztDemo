package com.example.quartzDemo.Thread;

import com.example.quartzDemo.info.ArrayInfo;
import org.quartz.JobDataMap;

public class MyThread implements Runnable {

    private String threadId;

    private ArrayInfo info;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public MyThread(String threadId, ArrayInfo info) {
        this.threadId = threadId;
        this.info = info;
    }

    public ArrayInfo getInfo() {
        return info;
    }

    public MyThread(ArrayInfo info) {
        this.info = info;
    }

    public void setInfo(ArrayInfo info) {
        this.info = info;
    }


    @Override
    public void run() {
        if (!this.info.isRunForever()) {
            System.out.println("Thread " + threadId + " is running!");
            info.insertNumber(info.getRemainingInsert());
            int remainingInsert = this.getInfo().getRemainingInsert();
            info.setRemainingInsert(remainingInsert - 1);
            if (remainingInsert == 0) {
                return;
            }
        }
    }

    public MyThread() {

    }
}
