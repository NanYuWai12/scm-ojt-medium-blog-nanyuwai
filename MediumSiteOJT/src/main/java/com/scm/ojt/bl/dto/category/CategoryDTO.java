package com.scm.ojt.bl.dto.category;

import com.scm.ojt.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Integer catId;
    private String name;
    private String createdAt;
    private String updatedAt;
    
    public CategoryDTO(Category cat) {
        super();
        this.catId =cat.getCatId();
        this.name =cat.getName();
    }
}
