package com.libr.demo.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    protected LocalDateTime timestamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected String reason;
    protected String path;
    protected String developerMessage;
    protected Map<?,?> data;

}
