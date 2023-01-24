package com.scm.ojt.bl.dto.user;
import java.util.Date;

import com.scm.ojt.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private String name;
    private String email;
    private String bio;
    private String password;
    private String profile;
    private Date createdAt;
    private Date updatedAt;
    
    public UserDTO(User user) {
        super();
        this.userId=user.getUserId();
        this.name =user.getName();
        this.email =user.getEmail();
        this.bio =user.getBio();
        this.password =user.getPassword();
        this.profile =user.getProfile();
        this.createdAt =user.getCreatedAt();
        this.updatedAt =user.getUpdatedAt();
    }
}
