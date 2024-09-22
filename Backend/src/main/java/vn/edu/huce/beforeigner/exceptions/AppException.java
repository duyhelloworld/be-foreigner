package vn.edu.huce.beforeigner.exceptions;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    
    private ResponseCode responseCode;

    public AppException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
