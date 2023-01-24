package com.scm.ojt.bl.dto.user;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.scm.ojt.web.form.UserForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Date timeStamp;
    
    private Integer responseCode;
    
    private String responseDescription;
    
    private Map<String, String> errors;
    
    private UserForm userForm;
    
    private UserDTO userDTO;
    
    private List<UserDTO> userList;
}
