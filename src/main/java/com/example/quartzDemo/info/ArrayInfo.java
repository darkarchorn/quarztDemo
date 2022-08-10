package com.example.quartzDemo.info;

import java.util.ArrayList;

public class ArrayInfo extends Thread{

    public ArrayList<Integer> number = new ArrayList<>();
    private int aimInsertion;
    private int inserted;
    private int remainingInsert;
    private boolean runForever;
    private Long repeatIntervalMs;
    private long initialOffsetMs;

    public int getAimInsertion() {
        return aimInsertion;
    }

    public void setAimInsertion(int aimInsertion) {
        this.aimInsertion = aimInsertion;
    }

    public int getRemainingInsert() {
        return remainingInsert;
    }

    public void setRemainingInsert(int remainingInsert) {
        this.remainingInsert = remainingInsert;
    }

    public int getInserted() {
        return inserted;
    }

    public void setInserted(int inserted) {
        this.inserted = inserted;
    }

    public boolean isRunForever() {
        return runForever;
    }

    public void setRunForever(boolean runForever) {
        this.runForever = runForever;
    }

    public Long getRepeatIntervalMs() {
        return repeatIntervalMs;
    }

    public void setRepeatIntervalMs(Long repeatIntervalMs) {
        this.repeatIntervalMs = repeatIntervalMs;
    }

    public long getInitialOffsetMs() {
        return initialOffsetMs;
    }

    public void setInitialOffsetMs(long initialOffsetMs) {
        this.initialOffsetMs = initialOffsetMs;
    }

    public void insertNumber(int i) {
        number.add(number.size());
    }
}
