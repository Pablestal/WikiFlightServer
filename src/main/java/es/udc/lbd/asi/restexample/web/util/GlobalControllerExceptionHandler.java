package es.udc.lbd.asi.restexample.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.udc.lbd.asi.restexample.web.exception.CredentialsAreNotValidException;
import es.udc.lbd.asi.restexample.web.exception.ResourceException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(ResourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO resourceExceptionHandler(ResourceException e) {
        logger.error(e.getMessage(), e);
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(CredentialsAreNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDTO badCredentialsExceptionHandler(CredentialsAreNotValidException e) {
        return new ErrorDTO("Bad Credentials");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return new ErrorDTO(e.getMessage());
    }
}
