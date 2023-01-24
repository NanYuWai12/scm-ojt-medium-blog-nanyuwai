package com.scm.ojt.bl.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Integer comId;
    private Integer postId;
    private Integer userId;
    private Integer parentCommentId;
    private String body;
    private String createdAt;
    private String updateAt;
}
