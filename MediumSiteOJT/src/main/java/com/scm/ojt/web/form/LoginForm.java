package com.scm.ojt.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginForm implements Serializable{

    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String email;
    
    @NotEmpty
    private String password;
}
