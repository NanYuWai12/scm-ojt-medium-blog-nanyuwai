package com.scm.ojt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.scm.ojt.web.form.PostForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;
    
    @Column(name = "deleted_at")
    private Date deletedAt;
    
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @Column(name = "del_flag",nullable = false)
    private Boolean delFlag;

    public Post(PostForm postForm) {
        super();
        this.postId =postForm.getPostId();
        this.userId =postForm.getUserId();
        this.title =postForm.getTitle();
        this.description =postForm.getDescription();
        this.image =postForm.getImage();
        this.delFlag =postForm.getDelFlag();
        
    }
}
