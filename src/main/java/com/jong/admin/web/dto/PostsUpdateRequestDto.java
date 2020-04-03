package com.jong.admin.web.dto;

import com.jong.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String contents;

    @Builder
    public PostsUpdateRequestDto(Posts entity) {
        this.title = entity.getTitle();
        this.contents = entity.getContents();
    }



}
