package com.jong.admin.web.dto;


import com.jong.admin.domain.posts.Posts;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PostsRequestDto {


    private String title;
    private String author;
    private String contents;

    @Builder
    public PostsRequestDto(String title, String author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .author(author)
                .contents(contents)
                .build();
    }
}
