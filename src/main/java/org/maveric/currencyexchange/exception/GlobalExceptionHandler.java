package org.maveric.currencyexchange.exception;

import org.maveric.currencyexchange.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.maveric.currencyexchange.constants.ExceptionConstants.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        List<ObjectError> listOfErrors = ex.getBindingResult().getAllErrors();

        String errorslist = "";

        for (ObjectError list : listOfErrors)
            errorslist = errorslist + list.getDefaultMessage() + ",";

        ex.getBindingResult().getAllErrors().forEach((err) -> {
            String errorMessage = err.getDefaultMessage();
            errorResponse.setStatus(HttpStatus.BAD_REQUEST);
            errorResponse.setMessage(ex.getFieldError().getField() + " " + errorMessage);
        });

        errorResponse.setMessage(errorslist);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> customerNotFound(CustomerNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> notAccessable(UnauthorizedAccessException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, FORBIDDEN_ACCESS_EXCEPTION_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> accountNotFound(AccountNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(AccountMisMatchException.class)
    public ResponseEntity<ErrorResponse> accountMisMatch(AccountMisMatchException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, ACCOUNT_MISMATCH_EXCEPTION_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> insufficientFunds(InsufficientFundsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, INSUFFICIENT_FUNDS_EXCEPTION_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(AccountConflictException.class)
    public ResponseEntity<ErrorResponse> sameAccount(AccountConflictException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, ACCOUNT_CONFLICT_EXCEPTION_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> usernameNotFound(InvalidCredentialsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, CREDENTIALS_CHECK_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> accountAlreadyExists(AccountAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ACCOUNT_ALREADY_EXISTS_MESSAGE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}