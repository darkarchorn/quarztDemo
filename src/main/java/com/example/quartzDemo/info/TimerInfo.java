package com.example.quartzDemo.info;

public class TimerInfo {
    private int totalFireCount;
    private int remainingFireCount;

    public int getRemainingFireCount() {
        return remainingFireCount;
    }

    public void setRemainingFireCount(int remainingFireCount) {
        this.remainingFireCount = remainingFireCount;
    }

    private boolean runForever;
    private Long repeatIntervalMs;
    private long initialOffsetMs;
    private String callbackData;

    public int getTotalFireCount() {
        return totalFireCount;
    }

    public void setTotalFireCount(int totalFireCount) {
        this.totalFireCount = totalFireCount;
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

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }
}