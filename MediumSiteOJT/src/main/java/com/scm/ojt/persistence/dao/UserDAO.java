package com.scm.ojt.persistence.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.entity.User;

public interface UserDAO {
    public void dbAddUser(User userForm);
    public String imageWrite(String path, MultipartFile file,int userId,String photo);
    public User dbGetUserById(int userId);
    public User dbGetUserByEmail(String email);
    public List<User> dbGetUserList();
    public void dbUpdateUser(User user);
    public String dbGetUserNameById(Integer userId);
}
