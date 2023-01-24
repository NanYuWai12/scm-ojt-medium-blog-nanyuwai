package com.scm.ojt.bl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.bl.dto.user.UserDTO;
import com.scm.ojt.web.form.UserForm;

public interface UserService {
    public void doAddUser(UserForm userForm);
    public String imageWrite(String path, MultipartFile file,int userId,String photo);
    public UserDTO doGetUserById(Integer userId);
    public UserDTO doGetUserByEmail(String email);
    public void doUpdateUser(UserForm userForm);
    public List<UserDTO> doGetUserList();
    public void doDeleteUser(UserForm userForm);
} 
