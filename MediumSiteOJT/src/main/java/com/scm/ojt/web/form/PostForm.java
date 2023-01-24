package com.scm.ojt.web.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.bl.dto.category.CategoryDTO;
import com.scm.ojt.bl.dto.post.PostDTO;
import com.scm.ojt.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {

    private Integer postId;
    private Integer userId;
    private String userName;
    private String title;
    private String description;
    private String image;
    private Boolean delFlag;
    private MultipartFile file;
    private String category;
    private List<CategoryDTO> catList;
    
    public PostForm(Post post) {
        super();
        this.postId=post.getPostId();
        this.userId =post.getUserId();
        this.title =post.getTitle();
        this.category =post.getCategory();
        this.description =post.getDescription();
        this.image =post.getImage();
    }

    public PostForm(PostDTO postDTO) {
        super();
        this.postId =postDTO.getPostId();
        this.userId =postDTO.getUserId();
        this.userName =postDTO.getUserName();
        this.title =postDTO.getTitle();
        this.description =postDTO.getDescription();
        this.image =postDTO.getImage();
        this.category =postDTO.getCategory();
    }
}
