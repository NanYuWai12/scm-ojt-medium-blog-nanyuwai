package com.scm.ojt.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scm.ojt.bl.dto.login.LoginResponseDTO;
import com.scm.ojt.bl.dto.login.RefreshTokenResponseDTO;
import com.scm.ojt.bl.dto.login.ValidationFailResponseDTO;
import com.scm.ojt.bl.service.LoginService;
import com.scm.ojt.entity.User;
import com.scm.ojt.security.CommonUtil;
import com.scm.ojt.security.HttpUtil;
import com.scm.ojt.security.JwtTokenUtil;
import com.scm.ojt.web.form.LoginForm;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class LoginRestController {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private HttpUtil httpUtil;
    
    @Autowired
    private CommonUtil commonUtil;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @RequestMapping(value = "/showLogin", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm,BindingResult br,HttpServletRequest request) throws JsonProcessingException{
        if(br.hasErrors()) {
           return new ResponseEntity<ValidationFailResponseDTO>(commonUtil.getValidationFailResponse(br),HttpStatus.BAD_REQUEST);
        }
        LoginResponseDTO response = new LoginResponseDTO();
        response.setTimeStamp(new Date());
        User user =this.loginService.doGetUserByEmail(loginForm.getEmail());
        if(user ==null) {
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseDescription("Login Fail");
            return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.BAD_REQUEST);
        }
        if(!CommonUtil.decodePassword(user.getPassword()).equals(loginForm.getPassword())) {
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseDescription("Login Fail");
            return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.BAD_REQUEST);
        }
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Login Success!");
        response.setToken(jwtTokenUtil.generateToken(user));
        return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws JsonProcessingException {
        String token = httpUtil.getBearerToken(request);
        String newToken = (token == null) ? null : jwtTokenUtil.refreshToken(token);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        response.setTimeStamp(new Date());
        if (token == null || newToken == null) {
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseDescription("Verification code was resent successfully.");
            return new ResponseEntity<RefreshTokenResponseDTO>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setToken(newToken);
            response.setResponseCode(HttpStatus.OK.value());
            response.setResponseDescription("Unable to resend email.");
            return new ResponseEntity<RefreshTokenResponseDTO>(response, HttpStatus.OK);
        }
    }
}
