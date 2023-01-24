package com.scm.ojt.bl.service;

import com.scm.ojt.entity.User;

public interface LoginService {
    public User doGetUserByEmail(String email);
}
