package com.scm.ojt.bl.dto.category;

import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCategoryDTO {

private Date timeStamp;
    
    private Integer responseCode;
    
    private String responseDescription;
    
    private Map<String, String> errors;
}
