package vn.edu.huce.beforeigner.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.utils.apiresponse.ApiResponse;

@Component
@RequiredArgsConstructor
public class Code403ExceptionHandler implements AccessDeniedHandler {

    private final AppObjectMapper appObjectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        var outStream = response.getOutputStream();
        ApiResponse<?> apiResponse = ApiResponse.error(ResponseCode.FORBIDDEN);
        appObjectMapper.writeValue(outStream, apiResponse);
        outStream.flush();
    }
    
}
