package com.scm.ojt.web.form;

import com.scm.ojt.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private Integer userId;
    private String name;
    private String email;
    private String bio;
    private String password;
    private String confirmPassword;
    private Boolean delFlag;
    
    public UserForm(User user) {
        super();
        this.userId =user.getUserId();
        this.name =user.getName();
        this.email =user.getEmail();
        this.bio =user.getBio();
        this.password =user.getPassword();
        this.delFlag =user.getDelFlag();
    }
}
