package com.scm.ojt.persistence.dao;

import java.util.List;

import com.scm.ojt.entity.Category;
import com.scm.ojt.entity.Post;

public interface PostDAO {

    public void dbAddPost(Post post);
    public void dbUpdatePost(Post post);
    public Post dbGetPostById(Integer postId);
    public List<Post> dbGetAllPost();
    public List<Category> dbGetCategories();
    public String dbGetCategoryById(Integer catId);
}
