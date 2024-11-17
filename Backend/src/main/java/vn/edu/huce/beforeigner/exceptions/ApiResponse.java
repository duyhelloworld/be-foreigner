package vn.edu.huce.beforeigner.exceptions;

import lombok.Data;

@Data
public class ApiResponse<T> {
    
    private int code;

    private T data;

    public ApiResponse() {
        this.code = ResponseCode.OK.getCode();
        this.data = null; 
    }

    public static <T> ApiResponse<T> error(ResponseCode responseCode, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(responseCode.getCode());
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(ResponseCode.OK.getCode());
        response.setData(data);
        return response;
    }

    
    public static ApiResponse<Void> ok() {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(ResponseCode.OK.getCode());
        response.setData(null);
        return response;
    }

    
}