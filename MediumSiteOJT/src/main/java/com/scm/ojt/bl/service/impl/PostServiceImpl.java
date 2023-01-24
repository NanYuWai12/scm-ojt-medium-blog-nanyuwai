package com.scm.ojt.bl.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scm.ojt.bl.dto.category.CategoryDTO;
import com.scm.ojt.bl.dto.post.PostDTO;
import com.scm.ojt.bl.service.PostService;
import com.scm.ojt.entity.Category;
import com.scm.ojt.entity.Post;
import com.scm.ojt.persistence.dao.PostDAO;
import com.scm.ojt.persistence.dao.UserDAO;
import com.scm.ojt.web.form.PostForm;

@Service
@Transactional
public class PostServiceImpl implements PostService{
    
    public static final List<String> CONTENT_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif");
    
    public static final String IMAGE_STORAGE_PATH = "resources//images//";
    
    @Autowired
    private PostDAO postDAO;
    
    @Autowired
    private UserDAO userDAO;

    public void doAddPost(PostForm postForm,MultipartFile file,String path) throws IOException {
        Post post =new Post(postForm);
        post.setCreatedAt(new Date());
        post.setDelFlag(Boolean.FALSE);
        String image =this.imageWrite(path, file, postForm);
        post.setImage(image);
        this.postDAO.dbAddPost(post);
    }

    public void doUpdatePost(PostForm postForm,MultipartFile file,String path) throws IOException {
        Post post =new Post(postForm);
        post.setUpdatedAt(new Date());
        String image =this.imageWrite(path, file, postForm);
        post.setImage(image);
        this.postDAO.dbUpdatePost(post);
    }

    public List<PostDTO> doGetPostList() {
        List<Post> postList =this.postDAO.dbGetAllPost();
        List<PostDTO> postDTOList =new ArrayList<>();
        String cateName;
        for(Post post :postList) {
            cateName= post.getCategory() !=null? this.postDAO.dbGetCategoryById(Integer.parseInt(post.getCategory())) :null;
            PostDTO postDTO =new PostDTO(post);
            postDTO.setCategory(cateName);
            postDTO.setUserName(this.userDAO.dbGetUserNameById(post.getUserId()));
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }

    public PostDTO doGetPostById(Integer postId) {
        String categoryName = null;
        Post post =this.postDAO.dbGetPostById(postId);
        if(post.getCategory() !=null) {
           categoryName =this.postDAO.dbGetCategoryById(Integer.parseInt(post.getCategory()));
        }
        String userName =this.userDAO.dbGetUserNameById(post.getUserId());
        PostDTO postDto =new PostDTO(post);
        postDto.setUserName(userName);
        postDto.setCategory(categoryName);
        return post !=null? postDto :null;
    }

    @Override
    public void doDeletePost(PostForm postForm) {
       Post post =this.postDAO.dbGetPostById(postForm.getPostId());
       post.setUpdatedAt(new Date());
       post.setDelFlag(Boolean.TRUE);
       this.postDAO.dbUpdatePost(post);
    }

    @Override
    public String doSearchphotoPath(String photo, String path) throws IOException {
        String checkFile ="";
        File file =new File(path+IMAGE_STORAGE_PATH+photo);
//        checkFile =file.exists()? photo :null;
        checkFile =IMAGE_STORAGE_PATH+photo;
        return checkFile;
    }

    @Override
    public String imageWrite(String path, MultipartFile file, PostForm postForm) throws IOException {
        String fullPath =path+IMAGE_STORAGE_PATH;
        String fileName =file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        String image =postForm.getImage();
        if (image != null) {
            String oldPhoto = fullPath + "/" + image;
            File oldFile = new File(oldPhoto);
            oldFile.delete();
        }
        byte[] bytes = file.getBytes();
        File dir = new File(fullPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileType =FilenameUtils.getExtension(file.getOriginalFilename());
        String imageName =fileName+"-"+System.currentTimeMillis()+"."+fileType;
        File serverFile = new File(dir.getAbsolutePath() + File.separator + imageName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return imageName;
    }

    @Override
    public List<CategoryDTO> cateList() {
        List<Category> cateList =this.postDAO.dbGetCategories();
        List<CategoryDTO> cateDTOList =new ArrayList<>();
        for(Category category :cateList) {
            CategoryDTO catDTO =new CategoryDTO(category);
            cateDTOList.add(catDTO);
        }
        return cateDTOList;
    }
  
}
