package vn.edu.huce.beforeigner.configurations;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;

@Component
@RequiredArgsConstructor
public class SecurityExceptionHandler implements AccessDeniedHandler {

    private final AppObjectMapper appObjectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType("application/json");
        var outStream = response.getOutputStream();
        ApiResponse<?> apiResponse = ApiResponse.error(ResponseCode.UNAUTHORIZED);
        appObjectMapper.writeValue(outStream, apiResponse);
        outStream.flush();
    }
    
}
