package br.com.carloscesargsf.candidatecase.exceptions.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@Schema(description = "Exception message of a request.")
public class ExceptionMessage implements Serializable {

    private static final long serialVersionUID = -7777638327009085078L;

    @Schema(description = "Time of the exception.", example = "2020-04-09T12:54:23.642Z", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private LocalDateTime timestamp;

    @Schema(description = "Error message.", example = "Bad request. Enter in touch with the administrator.")
    private String message;

    @Schema(description = "Requested URL.", example = "http://localhost:8080/test/1")
    private String requestURL;

    @Schema(description = "Requested URL HTTP method.", example = "GET")
    private String requestMethod;

    @Schema(description = "Fields with error.")
    private List<FieldMessage> fieldMessages = new LinkedList<>();

    private ExceptionMessage(String message, String requestURL, String requestMethod) {
        timestamp = LocalDateTime.now();
        this.message = message;
        this.requestURL = requestURL;
        this.requestMethod = requestMethod;
    }

    public static ExceptionMessage of(String message, HttpServletRequest request) {
        return new ExceptionMessage(message, request.getRequestURL().toString(), request.getMethod());
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public List<FieldMessage> getFieldMessages() {
        return unmodifiableList(fieldMessages);
    }

    public void addError(String fieldName, String message) {
        fieldMessages.add(new FieldMessage(fieldName, message));
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", requestURL='" + requestURL + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", fieldMessages=" + fieldMessages +
                '}';
    }

}
