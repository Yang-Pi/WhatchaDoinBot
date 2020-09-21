package mvc.model.controller;

import java.sql.Time;

public class PutDto {
    private long telegramId;
    private String sleepTimeBegin;
    private String sleepTimeEnd;
    private long checkingInterval;

    public PutDto(long telegramId, String sleepTimeBegin, String sleepTimeEnd, long checkingInterval) {
        this.telegramId = telegramId;
        this.sleepTimeBegin = sleepTimeBegin;
        this.sleepTimeEnd = sleepTimeEnd;
        this.checkingInterval = checkingInterval;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public String getSleepTimeBegin() {
        return sleepTimeBegin;
    }

    public String getSleepTimeEnd() {
        return sleepTimeEnd;
    }

    public long getCheckingInterval() {
        return checkingInterval;
    }
}
