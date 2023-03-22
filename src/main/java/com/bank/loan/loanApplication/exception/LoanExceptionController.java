package com.bank.loan.loanApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoanExceptionController {

        @ExceptionHandler(value = LoanNotFoundException.class)
        public ResponseEntity<Object> exception(LoanNotFoundException exception) {
            return new ResponseEntity<>("Loan not found", HttpStatus.NOT_FOUND);
        }

}
