package vn.edu.huce.beforeigner.utils.apiresponse;

import java.util.List;

import lombok.Data;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;

@Data
public class ApiResponse<T> {
    
    private int code;

    private T data;

    public static ApiResponse<List<String>> error(ResponseCode responseCode) {
        ApiResponse<List<String>> response = new ApiResponse<>();
        response.setCode(responseCode.getCode());
        response.setData(List.of(responseCode.getMessage()));
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