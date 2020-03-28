package com.jong.admin.web.service;

import com.jong.admin.domain.posts.Posts;
import com.jong.admin.domain.posts.PostsRepository;
import com.jong.admin.web.dto.PostsListResposneDto;
import com.jong.admin.web.dto.PostsRequestDto;
import com.jong.admin.web.dto.PostsResponseDto;
import com.jong.admin.web.dto.PostsUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequest dto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        postsRepository.update(id , dto.getTitle(), dto.getContents());
        return id;
    }

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
