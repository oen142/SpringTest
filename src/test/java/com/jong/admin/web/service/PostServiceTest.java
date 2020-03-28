package com.jong.admin.web.service;

import com.jong.admin.domain.posts.Posts;
import com.jong.admin.domain.posts.PostsRepository;
import com.jong.admin.web.dto.PostsRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }
    @Test
    public void DtoTest(){
        PostsRequestDto dto = PostsRequestDto.builder()
                .author("jojoldu@gmail.com")
                .contents("테스트")
                .title("테스트 타이틀")
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContents()).isEqualTo(dto.getContents());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}
