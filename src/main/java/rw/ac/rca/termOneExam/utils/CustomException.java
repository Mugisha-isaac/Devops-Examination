package rw.ac.rca.termOneExam.utils;

import org.springframework.http.HttpStatus;

public class CustomException {
    private HttpStatus httpStatus;
    private String mess;

    public CustomException(String message, HttpStatus httpStatus) {
//        super(message);
        this.mess = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
