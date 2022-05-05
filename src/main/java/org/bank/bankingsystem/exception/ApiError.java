package org.bank.bankingsystem.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    @JsonFormat(pattern = "DD-MM-YYYY hh:mm:ss")
    private LocalDateTime timestamp;

    private HttpStatus status;

    private String message;

    private String debugMessage;

    private ApiError(){
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message, Exception exception) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = exception.getMessage();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
