package com.jong.admin.web.dto;

import com.jong.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateRequest {

    private String title;
    private String contents;

    @Builder
    public PostsUpdateRequest(Posts entity) {
        this.title = entity.getTitle();
        this.contents = entity.getContents();
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .contents(contents)
                .build();
    }

}
