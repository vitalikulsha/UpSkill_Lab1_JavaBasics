package io.github.vitalikulsha.WebBasicsREST.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private final static Logger LOG = Logger.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {AuthorNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleAuthorNotFoundException(AuthorNotFoundException ex) {
        return new ApiErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(value = {CategoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return new ApiErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(value = {CategoryAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        return new ApiErrorResponse(400, ex.getMessage());
    }

    @ExceptionHandler(value = {AuthorAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleAuthorAlreadyExistsException(AuthorAlreadyExistsException ex) {
        return new ApiErrorResponse(400, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleUnknownException(Exception ex) {
        LOG.error(ex.getMessage());
        return new ApiErrorResponse(500, ex.getMessage());
    }
}
