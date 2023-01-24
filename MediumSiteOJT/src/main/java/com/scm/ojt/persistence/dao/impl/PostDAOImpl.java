package com.scm.ojt.persistence.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scm.ojt.entity.Category;
import com.scm.ojt.entity.Post;
import com.scm.ojt.persistence.dao.PostDAO;

@Repository
public class PostDAOImpl implements PostDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    private static final String SELECT_POST_BY_ID_HQL = "SELECT " 
                                                        + "p " 
                                                        + "FROM " 
                                                        + "Post p " 
                                                        + "WHERE " 
                                                        + "p.postId =: postId ";
    
    private static final String SELECT_POST_LIST_HQL = "SELECT " 
                                                        + "p " 
                                                        + "FROM " 
                                                        + "Post p "
                                                        + "WHERE "
                                                        + "p.delFlag <> 1"; 
    
    private static final String SELECT_CATEGORIES_LIST_HQL = "SELECT " 
                                                            + "c " 
                                                            + "FROM " 
                                                            + "Category c ";
    
    private static final String SELECT_CATEGORY_BY_ID_HQL = "SELECT " 
                                                            + "c.name " 
                                                            + "FROM " 
                                                            + "Category c " 
                                                            + "WHERE " 
                                                            + "c.catId =: catId ";
                                                   
    @Override
    public void dbAddPost(Post post) {
        this.sessionFactory.getCurrentSession().save(post);
        this.sessionFactory.getCurrentSession().flush();
        this.sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void dbUpdatePost(Post post) {
       this.sessionFactory.getCurrentSession().update(post);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Post dbGetPostById(Integer postId) {
       Query query =this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_BY_ID_HQL);
       query.setParameter("postId", postId);
        return (Post) query.uniqueResult();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<Post> dbGetAllPost() {
        Query query =this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_LIST_HQL);
        return query.getResultList();
    }

    @Override
    public List<Category> dbGetCategories() {
        Query query =this.sessionFactory.getCurrentSession().createQuery(SELECT_CATEGORIES_LIST_HQL);
        return query.getResultList();
    }

    @Override
    public String dbGetCategoryById(Integer catId) {
        Query query =this.sessionFactory.getCurrentSession().createQuery(SELECT_CATEGORY_BY_ID_HQL);
        query.setParameter("catId", catId);
        return (String) query.uniqueResult();
    }

}
