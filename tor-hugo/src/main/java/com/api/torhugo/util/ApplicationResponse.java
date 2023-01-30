package com.api.torhugo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationResponse {

    public HttpStatus created = HttpStatus.CREATED;
    public HttpStatus successful = HttpStatus.OK;
    public HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    public HttpStatus noContent = HttpStatus.NO_CONTENT;
    public HttpStatus internalError = HttpStatus.INTERNAL_SERVER_ERROR;
}
