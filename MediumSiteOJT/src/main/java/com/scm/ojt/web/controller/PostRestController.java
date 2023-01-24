package com.scm.ojt.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.ojt.bl.dto.post.PostDTO;
import com.scm.ojt.bl.dto.post.PostResponseDTO;
import com.scm.ojt.bl.service.PostService;
import com.scm.ojt.web.form.PostForm;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class PostRestController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/postList",method = RequestMethod.GET)
    public ResponseEntity<?> postList(HttpSession session) throws IOException{
        PostResponseDTO response =new PostResponseDTO();
        List<PostDTO> postList =this.postService.doGetPostList();
        for(int i=0;i< postList.size(); i++) {
            String path = session.getServletContext().getRealPath("/");
            postList.get(i).setImage(this.postService.doSearchphotoPath(postList.get(i).getImage(), path));
        }
        response.setCatList(this.postService.cateList());
        response.setPostList(postList);
        response.setTimeStamp(new Date());
        return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/postRegister",method = RequestMethod.GET)
    public ResponseEntity<?> postRegister(){
        PostForm postForm =new PostForm();
        PostResponseDTO response =new PostResponseDTO();
        response.setCatList(this.postService.cateList());
        response.setPostForm(postForm);
        response.setTimeStamp(new Date());
        return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/postRegister",method =  RequestMethod.POST)
    public ResponseEntity<?> postRegister(@ModelAttribute PostForm postForm, HttpSession session ) throws IOException{
        PostResponseDTO response =new PostResponseDTO();
        String path =session.getServletContext().getRealPath("/");
        response.setTimeStamp(new Date());
        this.postService.doAddPost(postForm,postForm.getFile(),path);
        postForm.setFile(null);
        response.setPostForm(postForm);
        return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/editPost",method =  RequestMethod.GET)
    public ResponseEntity<?> editPost(@RequestParam Integer postId,HttpSession session) throws IOException{
        PostResponseDTO response =new PostResponseDTO();
        String path =session.getServletContext().getRealPath("/");
        PostDTO postDTO =this.postService.doGetPostById(postId);
        response.setImage(this.postService.doSearchphotoPath(postDTO.getImage(), path));
        response.setTimeStamp(new Date());
        response.setTitle(postDTO.getTitle());
        response.setDescription(postDTO.getDescription());
        response.setUserId(postDTO.getUserId());
        response.setPostId(postDTO.getPostId());
        return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/postDetail/{postId}",method = RequestMethod.GET)
    public ResponseEntity<?> postDetail(@PathVariable Integer postId,HttpSession session) throws IOException{
        PostResponseDTO response =new PostResponseDTO();
        String path =session.getServletContext().getRealPath("/");
        PostDTO postDTO =this.postService.doGetPostById(postId);
        response.setImage(this.postService.doSearchphotoPath(postDTO.getImage(), path));
        response.setTimeStamp(new Date());
        response.setPostDTO(this.postService.doGetPostById(postId));
        return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatePost",method = RequestMethod.POST)
    public ResponseEntity<?> updatedPost(@ModelAttribute PostForm postForm,HttpSession session) throws IOException{
        PostResponseDTO response =new PostResponseDTO();
        String path =session.getServletContext().getRealPath("/");
        response.setTimeStamp(new Date());
        this.postService.doUpdatePost(postForm,postForm.getFile(),path);
        response.setResponseDescription("Post Updated Successfully!");
        postForm.setFile(null);
        response.setPostForm(postForm);
        response.setImage(this.postService.doSearchphotoPath(postForm.getImage(), path));
        return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deletePost",method = RequestMethod.GET)
    public ResponseEntity<?> deletePost(@RequestParam Integer postId){
        PostResponseDTO response =new PostResponseDTO();
        PostDTO post =this.postService.doGetPostById(postId);
        response.setPostDTO(post);
        response.setTimeStamp(new Date());
        return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
    }
    
     @RequestMapping(value = "/deletePost",method = RequestMethod.POST)
     public ResponseEntity<?> deletePost(@Valid @RequestBody PostForm postForm){
         PostResponseDTO response =new PostResponseDTO();
         response.setTimeStamp(new Date());
         this.postService.doDeletePost(postForm);
         response.setResponseDescription("Post Deleted Successfully!");
         return new ResponseEntity<PostResponseDTO>(response,HttpStatus.OK);
     }
}