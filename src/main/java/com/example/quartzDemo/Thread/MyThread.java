package com.example.quartzDemo.Thread;

import com.example.quartzDemo.info.ArrayInfo;
import org.quartz.JobDataMap;

public class MyThread implements Runnable {

    private Thread t;
    private int threadId;

    private ArrayInfo info;

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public MyThread(int threadId, ArrayInfo info) {
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
        try {
            if (!this.info.isRunForever()) {
                info.insertNumber(this.threadId);
                int remainingInsert = this.getInfo().getRemainingInsert();
                info.setRemainingInsert(remainingInsert - 1);
                if (remainingInsert == 0) {
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Thread " +  threadId + " interrupted.");
        }
    }

    public MyThread() {

    }
}
