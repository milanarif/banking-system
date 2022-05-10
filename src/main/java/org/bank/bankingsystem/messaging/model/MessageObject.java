package org.bank.bankingsystem.messaging.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class MessageObject {
    
    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm:ss")
    private LocalDateTime localDatetime;

    public MessageObject(String username, LocalDateTime localDatetime) {
        this.username = username;
        this.localDatetime = localDatetime;
    }

    public MessageObject() {}

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLocalDatetime() {
        return localDatetime;
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "username=" + username +
                ", localDatetime=" + localDatetime +
                '}';
    }
}
