package com.jong.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p order by p.id desc ")
    public List<Posts> findAllDesc();

    @Modifying    // update , delete Query시 @Modifying 어노테이션을 추가
    @Query(value = "UPDATE Posts p SET p.title = :title , p.contents = :contents WHERE  p.id = :id", nativeQuery = false)
    Integer update(@Param("id") Long id,
                @Param("title") String name,
                @Param("contents") String contents);

}
