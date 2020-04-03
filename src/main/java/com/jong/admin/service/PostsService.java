package com.jong.admin.service;

import com.jong.admin.domain.posts.PostsRepository;
import com.jong.admin.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jong.admin.domain.posts.Posts;
import com.jong.admin.web.dto.PostsListResposneDto;
import com.jong.admin.web.dto.PostsResponseDto;
import com.jong.admin.web.dto.PostsUpdateRequestDto;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        posts.update(dto.getTitle() , dto.getContents());
        return id;
    }
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당사용자가 없습니다. id =" +id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당사용자가 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResposneDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResposneDto::new)
                .collect(Collectors.toList());
    }
}
