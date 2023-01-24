package com.scm.ojt.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.scm.ojt.bl.dto.login.LoginDTO;

import io.jsonwebtoken.ExpiredJwtException;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    HttpServletRequest req;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT");
//        response.setHeader("Access-Control-Allow-Headers",
//                "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Authorization, Access-Control-Request-Method, Access-Control-Request-Headers");
        String token = httpUtil.getBearerToken(request);
        System.out.println(token);
        if (token == null) {
            httpUtil.getLoginFailResponse(response, HttpStatus.UNAUTHORIZED.value());
            response.setStatus(401);
            return false;
        }
        LoginDTO loginUser = null;
        try {
            if (jwtTokenUtil.isValidToken(token)) {
                loginUser = jwtTokenUtil.getLoginDTO(token);
            }
        } catch (ExpiredJwtException e) {
            System.out.println(e);
            httpUtil.getLoginFailResponse(response, HttpStatus.UNAUTHORIZED.value());
            return false;
        } catch (Exception e) {
            System.out.println(e);
            httpUtil.getLoginFailResponse(response, HttpStatus.BAD_REQUEST.value());
            return false;
        }
        return true;
    }
}
