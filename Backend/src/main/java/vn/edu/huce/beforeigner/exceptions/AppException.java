package vn.edu.huce.beforeigner.exceptions;

import lombok.Getter;
import vn.edu.huce.beforeigner.enums.ResponseCode;

@Getter
public class AppException extends RuntimeException {
    
    private ResponseCode responseCode;

    public AppException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
