package vn.edu.huce.beforeigner.exceptions;

import lombok.Getter;
import vn.edu.huce.beforeigner.enums.ResponseCode;

public class AppException extends RuntimeException {
    @Getter
    private ResponseCode responseCode;

    public AppException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
