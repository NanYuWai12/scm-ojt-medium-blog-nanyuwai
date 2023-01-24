package com.scm.ojt.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.ojt.bl.dto.user.UserDTO;
import com.scm.ojt.bl.dto.user.UserResponseDTO;
import com.scm.ojt.bl.service.UserService;
import com.scm.ojt.web.form.UserForm;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public ResponseEntity<?> userList(){
        List<UserDTO> userList =this.userService.doGetUserList();
        UserResponseDTO response =new UserResponseDTO();
        response.setUserList(userList);
        response.setTimeStamp(new Date());
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
       
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ResponseEntity<?> register(){
        UserForm userForm =new UserForm();
        UserResponseDTO response =new UserResponseDTO();
        response.setUserForm(userForm);
        response.setTimeStamp(new Date());
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ResponseEntity<?> hello(){
        String hello ="Hello World";
        return new ResponseEntity<String>(hello,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> userRegister(@RequestBody UserForm userForm){
        UserResponseDTO response =new UserResponseDTO();
        response.setUserForm(userForm);
//        LoginDTO loginDTO =this.httpUtil.getLoginDTO(request);
        response.setTimeStamp(new Date());
        if(hasValidateError(response, userForm)) {
            response.setResponseCode(101);
            response.setResponseDescription("Validation Error!");
            return new ResponseEntity<UserResponseDTO>(response,HttpStatus.ACCEPTED); 
        }else {
                this.userService.doAddUser(userForm);
                response.setResponseDescription("User Registration Successfully!");
            }
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/editUser", method =  RequestMethod.GET)
    public ResponseEntity<?> editUser(@RequestParam Integer userId){
        UserResponseDTO response =new UserResponseDTO();
        UserDTO userDTO =this.userService.doGetUserById(userId);
        response.setUserDTO(userDTO);
        response.setTimeStamp(new Date());
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserForm userForm,BindingResult br){
        UserResponseDTO response =new UserResponseDTO();
        response.setTimeStamp(new Date());
        if(hasValidateError(response, userForm)) {
            response.setResponseCode(101);
            response.setResponseDescription("Validation Error!");
            return new ResponseEntity<UserResponseDTO>(response,HttpStatus.ACCEPTED); 
        }
        this.userService.doUpdateUser(userForm);
        response.setResponseDescription("User Updated Successfully!");
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/userDetail",method = RequestMethod.GET)
    public ResponseEntity<?> userDetail(@RequestParam Integer userId){
        UserResponseDTO response =new UserResponseDTO();
        UserDTO userDTO =this.userService.doGetUserById(userId);
        if(userDTO ==null) {
            response.setResponseCode(101);
            response.setResponseDescription("User does not exist");
            return new ResponseEntity<UserResponseDTO>(response,HttpStatus.ACCEPTED);
        }
        response.setUserDTO(userDTO);
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public ResponseEntity<?> deleteUser(@RequestParam Integer userId){
        UserResponseDTO response =new UserResponseDTO();
        UserDTO userDTO =this.userService.doGetUserById(userId);
        response.setUserDTO(userDTO);
        UserForm userForm =new UserForm();
        response.setUserForm(userForm);
        response.setTimeStamp(new Date());
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@Valid @RequestBody UserForm userForm){
        UserResponseDTO response =new UserResponseDTO();
        response.setTimeStamp(new Date());
        this.userService.doDeleteUser(userForm);
        response.setResponseDescription("User Deleted Successfully!");
        return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
    }
    
    private boolean hasValidateError(UserResponseDTO response, UserForm userForm) {
        boolean hasError = false;
        Map<String, String> errors = new HashMap<String, String>();
        UserDTO userDTO =this.userService.doGetUserByEmail(userForm.getEmail());
        
        if(userForm.getName().isEmpty()) {
            errors.put("nameError", "Name is a required field");
            hasError =true;
        }
        
        if(userForm.getEmail().isEmpty()) {
            errors.put("emailError", "Email is a required field");
            hasError =true;
        }

        if(userForm.getPassword().isEmpty()) {
            errors.put("passwordError", "Password is a required field");
            hasError =true;
        }
        if(userDTO == null && userForm.getConfirmPassword().isEmpty()) {
            errors.put("confirmPasswordError", "Confirm password is a required field");
            hasError =true;
        }
       if(!userForm.getPassword().equals(userForm.getConfirmPassword())){
           errors.put("passwordError", "password and confirm password must be equal");
           hasError =true;
       }
        if(userDTO != null && userDTO.getUserId() ==null) {
            errors.put("duplicateError", "User already exist");
            hasError =true;
        }
        response.setErrors(errors);
        return hasError;
    }
}
