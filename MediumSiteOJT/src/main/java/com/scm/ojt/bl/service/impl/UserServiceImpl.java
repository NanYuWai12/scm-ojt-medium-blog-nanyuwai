package com.scm.ojt.bl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.bl.dto.user.UserDTO;
import com.scm.ojt.bl.service.UserService;
import com.scm.ojt.entity.User;
import com.scm.ojt.persistence.dao.UserDAO;
import com.scm.ojt.security.CommonUtil;
import com.scm.ojt.web.form.UserForm;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    
    public void doAddUser(UserForm userForm) {
       User user =new User(userForm);
       user.setPassword(CommonUtil.encodePassword(userForm.getPassword()));
       user.setCreatedAt(new Date());
       user.setDelFlag(Boolean.FALSE);
       this.userDAO.dbAddUser(user);
    }
    
    @Override
    public void doUpdateUser(UserForm userForm) {
       User user =new User(userForm);
       user.setPassword(CommonUtil.encodePassword(userForm.getPassword()));
       user.setUpdatedAt(new Date());
       this.userDAO.dbUpdateUser(user);
    }

    public String imageWrite(String path, MultipartFile file, int userId, String photo) {
       
        return null;
    }

    public UserDTO doGetUserById(Integer userId) {
        User user =this.userDAO.dbGetUserById(userId);
        return user !=null ? new UserDTO(user) : null;
    }

    public UserDTO doGetUserByEmail(String email) {
      User user =this.userDAO.dbGetUserByEmail(email);
      return user !=null ? new UserDTO(user) :null;
    }

    public List<UserDTO> doGetUserList() {
        List<User> userList =this.userDAO.dbGetUserList();
        List<UserDTO> userDtoList =new ArrayList<UserDTO>();
        for(User user :userList) {
            UserDTO userDto =new UserDTO(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public void doDeleteUser(UserForm userForm) {
       User user =new User(userForm);
       user.setUpdatedAt(new Date());
       user.setDelFlag(Boolean.TRUE);
       this.userDAO.dbUpdateUser(user); 
    }
}
