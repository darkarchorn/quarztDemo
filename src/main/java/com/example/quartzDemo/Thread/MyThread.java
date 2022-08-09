package com.example.quartzDemo.Thread;

import com.example.quartzDemo.info.ArrayInfo;
import org.quartz.JobDataMap;

public class MyThread implements Runnable{
    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    private int threadId;

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

    private ArrayInfo info;

    @Override
    public void run() {
        if(!this.info.isRunForever()) {
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
