package com.scm.ojt.persistence.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scm.ojt.entity.User;
import com.scm.ojt.persistence.dao.LoginDAO;

@SuppressWarnings("deprecation")
@Repository
public class LoginDAOImpl implements LoginDAO{
    
    private static final String SELECT_LOGIN_HQL = "SELECT " 
                                                    + "u " 
                                                    + "FROM " 
                                                    + "User u " 
                                                    + "WHERE " 
                                                    + "u.email =: email ";
    
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("rawtypes")
    @Override
    public User dbGetUserByEmail(String email) {
        StringBuffer stringBuffer =new StringBuffer(SELECT_LOGIN_HQL);
        Query query =this.sessionFactory.getCurrentSession().createQuery(stringBuffer.toString());
        query.setParameter("email", email);
        User user =(User) query.uniqueResult();
         return user;
    }
}
