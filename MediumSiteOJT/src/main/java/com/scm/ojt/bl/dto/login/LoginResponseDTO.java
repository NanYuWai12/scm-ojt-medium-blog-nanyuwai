package com.scm.ojt.bl.dto.login;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO implements Serializable{

    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    
    private static final long serialVersionUID = 1L;
    private Date timeStamp;
    private Integer responseCode;
    private String responseDescription;
    private String token;
    
    public LoginResponseDTO(Integer responseCode, String responseDescription) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
    }
}
