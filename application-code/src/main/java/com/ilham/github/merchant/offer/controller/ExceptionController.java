package com.ilham.github.merchant.offer.controller;

import com.ilham.github.merchant.offer.util.MerchantOfferConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller Advice for handling any exceptions thrown in the application
 */

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    /**
     * Exception Handler for Illegal Argument Exceptions
     * @param ex The exception thrown
     * @param request The webrequest
     * @return A Response Entity with the exception message with a HTTP 400 Bad Request status
     */

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(MerchantOfferConstants.EXCEPTION_RESPONSE_MESSAGE + ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception Handler for General Runtime Exceptions
     * @param ex The exception thrown
     * @param request The webrequest
     * @return A Response Entity with the exception message with a HTTP 500 Internal Server Error status
     */

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(MerchantOfferConstants.EXCEPTION_RESPONSE_MESSAGE + ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
