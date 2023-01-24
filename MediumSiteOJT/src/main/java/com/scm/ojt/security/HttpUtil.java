package com.scm.ojt.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.scm.ojt.bl.dto.login.LoginDTO;
import com.scm.ojt.bl.dto.login.LoginResponseDTO;
@Component
public class HttpUtil {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    public String getBearerToken(HttpServletRequest request) {
        Enumeration header = request.getHeaderNames();
        while(header.nextElement() != null) {
            System.out.println(header.equals("Authorization"));
        }
        String requestHeader = request.getHeader("Authorization");
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            return requestHeader.substring(7);
        }
        return null;
    }

    public LoginDTO getLoginDTO(HttpServletRequest request) {
        String requestHeader = request.getHeader("Authorization");
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String token = requestHeader.substring(7);
            return this.jwtTokenUtil.getLoginDTO(token);
        }
        return null;
    }
    
    public void getLoginFailResponse(HttpServletResponse response, int status) throws IOException {
        LoginResponseDTO responseDTO = null;
        if (status == HttpStatus.FORBIDDEN.value()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            responseDTO = new LoginResponseDTO(HttpStatus.FORBIDDEN.value(), "Forbidden");
        } else if (status == HttpStatus.BAD_REQUEST.value()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO = new LoginResponseDTO(HttpStatus.BAD_REQUEST.value(), "Bad Request");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseDTO = new LoginResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(new Gson().toJson(responseDTO));
        out.flush();
    }
}
