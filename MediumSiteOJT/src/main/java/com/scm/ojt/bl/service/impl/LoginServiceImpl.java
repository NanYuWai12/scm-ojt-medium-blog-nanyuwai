package com.scm.ojt.bl.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.scm.ojt.bl.service.LoginService;
import com.scm.ojt.entity.User;
import com.scm.ojt.persistence.dao.LoginDAO;

@Transactional
@Service
@Primary
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDAO loginDAO;
    
    public User doGetUserByEmail(String email) {
        User user =this.loginDAO.dbGetUserByEmail(email);
        return user;
    }
}
