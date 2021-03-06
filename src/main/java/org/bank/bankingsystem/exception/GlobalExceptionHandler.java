package org.bank.bankingsystem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({CustomException.NotFoundException.class})
    public ResponseEntity<Object> notFoundException(CustomException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Resource not found";

        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, errorMessage, exception));
    }

    @ExceptionHandler({CustomException.AlreadyExistsException.class})
    public ResponseEntity<Object> alreadyExistsException(CustomException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Already Exists";

        return buildResponseEntity(new ApiError(HttpStatus.IM_USED, errorMessage, exception));
    }

    @ExceptionHandler({CustomException.InsufficientFundsException.class})
    public ResponseEntity<Object> In(CustomException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Insufficient funds";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, exception));
    }

    @ExceptionHandler({CustomException.UnauthorizedTransfer.class})
    public ResponseEntity<Object> unauthorizedTransfer(CustomException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "NO AUTHORIZATION";
        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, errorMessage, exception));
    }
    
    @ExceptionHandler({CustomException.RemoveAdminException.class})
    public ResponseEntity<Object> removeAdminException(CustomException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Not allowed to remove admin";
        return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, errorMessage, exception));
    }

    @ExceptionHandler({CustomException.InvalidUserDetails.class})
    public ResponseEntity<Object> invalidUserDetails(CustomException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Could not create user, invalid details";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, exception));
    }
}
