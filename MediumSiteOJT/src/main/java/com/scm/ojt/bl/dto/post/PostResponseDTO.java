package com.scm.ojt.bl.dto.post;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.scm.ojt.bl.dto.category.CategoryDTO;
import com.scm.ojt.web.form.PostForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {

    private Date timeStamp;

    private Integer responseCode;

    private String responseDescription;

    private Map<String, String> errors;

    private Integer postId;
    private Integer userId;
    private String title;
    private String description;
    private String image;
    private Boolean delFlag;

    private PostForm postForm;

    private PostDTO postDTO;
    
    private List<CategoryDTO> catList;

    private List<PostDTO> postList;
}
