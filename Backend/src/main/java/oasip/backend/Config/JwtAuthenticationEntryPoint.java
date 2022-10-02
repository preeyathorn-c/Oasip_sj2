package oasip.backend.Config;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {




        String message = request.getAttribute("exception").toString();
        System.out.println(message);
//        System.out.println(request.getAttribute("exception"));
//        if (authException.getCause() != null) {
//            message = authException.getCause().toString() + " " + authException.getMessage();
//        } else {
//            message = authException.getMessage();
//        }

//        byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));

//        response.getOutputStream().write(body);

//        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }
}