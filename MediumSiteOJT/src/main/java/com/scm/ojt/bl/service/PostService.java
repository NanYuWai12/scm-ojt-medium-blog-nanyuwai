package com.scm.ojt.bl.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.bl.dto.category.CategoryDTO;
import com.scm.ojt.bl.dto.post.PostDTO;
import com.scm.ojt.web.form.PostForm;

public interface PostService {

    public void doAddPost(PostForm postForm,MultipartFile file,String filePath) throws IOException;

    public void doUpdatePost(PostForm postForm,MultipartFile file,String filePath) throws IOException;

    public List<PostDTO> doGetPostList();

    public PostDTO doGetPostById(Integer postId);

    public void doDeletePost(PostForm postForm);

    public String doSearchphotoPath(String image, String path) throws IOException;

    public String imageWrite(String path,MultipartFile file,PostForm postForm) throws IOException;
    
    public List<CategoryDTO> cateList();
    
}
