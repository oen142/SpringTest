package com.jong.admin.web.controller;


import com.jong.admin.web.dto.PostsRequestDto;
import com.jong.admin.web.dto.PostsResponseDto;
import com.jong.admin.web.dto.PostsUpdateRequest;
import com.jong.admin.web.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class IndexRestController {


    private PostsService postsService;


    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsRequestDto dto){
        return postsService.save(dto);
    }

    @PutMapping("/posts/update/{id}")
    public Long update(@PathVariable Long id , @RequestBody PostsUpdateRequest dto){
        return postsService.update(id , dto);
    }

}
