package com.scm.ojt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.scm.ojt.web.form.UserForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamicUpdate
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "bio")
    private String bio;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "profile")
    private String profile;
    
    @Column(name = "del_flag",nullable = false)
    private Boolean delFlag;
    
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;
    
    @Column(name = "updated_at")
    private Date updatedAt;

    public User(UserForm userForm) {
        super();
        this.userId =userForm.getUserId();
        this.name =userForm.getName();
        this.email =userForm.getEmail();
        this.bio =userForm.getBio();
        this.password =userForm.getPassword();
        this.delFlag =userForm.getDelFlag();
    }
}
