package com.scm.ojt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
     private Integer comId;
    
    @Column(name = "post_id")
     private Integer postId;
    
    @Column(name = "user_id")
     private Integer userId;
    
    @Column(name = "parent_comment_id")
     private Integer parentCommentId;
    
    @Column(name = "body")
     private String body;
    
    @Column(name = "created_at")
     private String createdAt;
    
    @Column(name = "update_at")
     private String updateAt;
}
