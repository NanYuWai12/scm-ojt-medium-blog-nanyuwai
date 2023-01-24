package com.scm.ojt.bl.dto.login;
import java.io.Serializable;

import com.scm.ojt.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable{

    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    
    private static final long serialVersionUID = 1L;
    
    private Integer userId;
    private String name;
    private String password;
    private String email;
    
    public LoginDTO(User user) {
        super();
        this.userId =user.getUserId();
        this.name =user.getName();
        this.email =user.getEmail();
        this.password =user.getPassword();
    }
}
