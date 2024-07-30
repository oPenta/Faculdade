package br.com.terceiroperiodo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPhotoException.class)
    public ResponseEntity<Object> handlePhotoError(InvalidPhotoException e) {
        log.error("handlePhotoError() - [ERROR] - at method <{}> - message <{}>", e.getStackTrace()[0].getMethodName(), e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    //...
}
