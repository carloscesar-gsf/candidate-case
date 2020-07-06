package br.com.carloscesargsf.candidatecase.exceptions;

import br.com.carloscesargsf.candidatecase.exceptions.info.ExceptionMessage;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler({DatabaseException.class, ParamFormatException.class, ApiBusinessException.class})
    @ApiResponse(responseCode = "400", description = "Bad request!")
    public ResponseEntity<ExceptionMessage> badRequest(BaseException e, HttpServletRequest request) {
        return getResponseEntity(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    @ApiResponse(responseCode = "401", description = "User must be authenticated!")
    public ResponseEntity<ExceptionMessage> unauthorized(BaseException e, HttpServletRequest request) {
        return getResponseEntity(e, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ApiResponse(responseCode = "403", description = "User does not have permission!")
    public ResponseEntity<ExceptionMessage> forbidden(BaseException e, HttpServletRequest request) {
        return getResponseEntity(e, request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Resource not found!")
    public ResponseEntity<ExceptionMessage> notFound(BaseException e, HttpServletRequest request) {
        return getResponseEntity(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class,
            ConstraintViolationException.class})
    @ApiResponse(responseCode = "422", description = "Invalid info!")
    public ResponseEntity<ExceptionMessage> methodArgumentNotValidException(Exception e, HttpServletRequest request) {
        return getResponseEntity(e, request, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<ExceptionMessage> getResponseEntity(Exception e, HttpServletRequest request, HttpStatus status) {
        ExceptionMessage exceptionMessage = ExceptionMessage.of(e.getMessage(), request);

        if (e instanceof MethodArgumentNotValidException) {
            for (FieldError fe : ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors()) {
                exceptionMessage.addError(fe.getField(), fe.getDefaultMessage());
            }
        } else if (e instanceof BindException) {
            for (FieldError fe : ((BindException) e).getBindingResult().getFieldErrors()) {
                exceptionMessage.addError(fe.getField(), fe.getDefaultMessage());
            }
        } else if (e instanceof ConstraintViolationException) {
            for (ConstraintViolation constraintViolation : ((ConstraintViolationException) e).getConstraintViolations()) {
                exceptionMessage.addError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }

        return ResponseEntity
                .status(status.value())
                .body(exceptionMessage);
    }

}
