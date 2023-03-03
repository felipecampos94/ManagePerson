package br.com.manageperson.controller.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Data
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(Instant timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
