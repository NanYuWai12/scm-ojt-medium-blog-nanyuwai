package com.scm.ojt.persistence.dao;

import com.scm.ojt.entity.User;

public interface LoginDAO {

    public User dbGetUserByEmail(String email);
}
