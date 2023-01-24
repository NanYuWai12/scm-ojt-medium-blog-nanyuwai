package com.scm.ojt.bl.dto.post;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.entity.Post;
import com.scm.ojt.web.form.PostForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Integer postId;
    private Integer userId;
    private String userName;
    private String title;
    private String description;
    private String image;
    private MultipartFile file;
    private String category;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;
    private Boolean delFlag;
    
    public PostDTO(Post post) {
        super();
        this.postId =post.getPostId();
        this.userId =post.getUserId();
        this.title =post.getTitle();
        this.description =post.getDescription();
        this.image =post.getImage();
        this.category =post.getCategory();
        this.createdAt =post.getCreatedAt();
        this.deletedAt =post.getDeletedAt();
        this.updatedAt =post.getUpdatedAt();
        this.delFlag =post.getDelFlag();
    }
    
    public PostDTO(PostForm postForm) {
        this.postId =postForm.getPostId();
        this.userId =postForm.getUserId();
        this.title =postForm.getTitle();
        this.description =postForm.getDescription();
        this.image =postForm.getImage();
        this.category =postForm.getCategory();
    }
}
