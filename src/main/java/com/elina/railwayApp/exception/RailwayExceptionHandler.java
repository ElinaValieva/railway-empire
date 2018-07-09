package com.elina.railwayApp.exception;

import com.elina.railwayApp.DTO.ErrorDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;

@ControllerAdvice
@Log4j
public class RailwayExceptionHandler {


    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> handleBusinessLogicException(BusinessLogicException ex) {
        log.debug(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(new ErrorDTO(ex.getError()));
    }

    @ExceptionHandler({ParseException.class, IOException.class})
    public ResponseEntity<?> handleParseException(Exception ex) {
        log.debug(ex.getMessage(), ex);
        if (ex instanceof ParseException)
            return ResponseEntity.badRequest().body(new ErrorDTO(ErrorCode.PARSE_EXCEPTION.getMessage()));
        else return ResponseEntity.badRequest().body(ErrorCode.IO_EXCEPTION.getMessage());
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<?> handleMailException(Exception ex) {
        log.debug(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(new ErrorDTO(ErrorCode.MAIL_EXCEPTION.getMessage()));
    }
}
