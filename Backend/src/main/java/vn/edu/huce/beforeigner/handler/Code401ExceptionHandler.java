package vn.edu.huce.beforeigner.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
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
public class Code401ExceptionHandler implements AuthenticationEntryPoint {

    private final AppObjectMapper appObjectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        var outStream = response.getOutputStream();
        appObjectMapper.writeValue(outStream, ApiResponse.error(ResponseCode.UNAUTHORIZED));
        outStream.flush();
    }

}
