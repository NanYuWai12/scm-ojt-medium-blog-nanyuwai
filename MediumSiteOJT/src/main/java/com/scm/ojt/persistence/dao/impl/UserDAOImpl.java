package com.scm.ojt.persistence.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.entity.User;
import com.scm.ojt.persistence.dao.UserDAO;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    
    private static final String SELECT_USER_LIST_SQL = "SELECT " 
                                                        + " u " 
                                                        + "FROM "
                                                        + "User u "
                                                        + "WHERE "
                                                        + "u.delFlag <> 1";
                                                        
    private static final String SELECT_USER_BY_EMAIL_HQL = "SELECT " 
                                                            + "u " 
                                                            + "FROM " 
                                                            + "User u " 
                                                            + "WHERE " 
                                                            + "u.email =: email ";
                                                                                     
    private static final String SELECT_USER_BY_ID_HQL = "SELECT " 
                                                            + "u " 
                                                            + "FROM " 
                                                            + "User u " 
                                                            + "WHERE " 
                                                            + "u.userId =: userId ";
                                                    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void dbAddUser(User user) {
       this.sessionFactory.getCurrentSession().save(user);
       this.sessionFactory.getCurrentSession().flush();
       this.sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void dbUpdateUser(User user) {
       this.sessionFactory.getCurrentSession().update(user);
       this.sessionFactory.getCurrentSession().flush();
       this.sessionFactory.getCurrentSession().clear(); 
    }
    public String imageWrite(String path, MultipartFile file, int userId, String photo) {
        
        return null;
    }

    public User dbGetUserById(int userId) {
        StringBuffer stringBuffer =new StringBuffer(SELECT_USER_BY_ID_HQL);
        Query query =this.sessionFactory.getCurrentSession().createQuery(stringBuffer.toString());
        query.setParameter("userId", userId);
        User user =(User) query.uniqueResult();
        return user;
    }

    @SuppressWarnings("rawtypes")
    public User dbGetUserByEmail(String email) {
        StringBuffer stringBuffer =new StringBuffer(SELECT_USER_BY_EMAIL_HQL);
        Query query =this.sessionFactory.getCurrentSession().createQuery(stringBuffer.toString());
        query.setParameter("email", email);
        User user =(User) query.uniqueResult();
        return user;
    }

    @SuppressWarnings("rawtypes")
    public List<User> dbGetUserList() {
        Query query =this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_LIST_SQL);
        return query.getResultList();
    }

    @Override
    public String dbGetUserNameById(Integer userId) {
        Query query =this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_ID_HQL);
        query.setParameter("userId", userId);
        User user =(User) query.uniqueResult();
        String userName =user.getName();
        return userName;
    }
}
