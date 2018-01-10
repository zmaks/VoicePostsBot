package com.zheltoukhov.joker.repository;

import com.zheltoukhov.joker.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maksim on 21.10.2017.
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findTop2ByIdNotInOrderByMarkDesc(List<String> ids);
    List<Post> findTop3ByIdNotInOrderByMark2Desc(List<String> ids);
    @Query("{$where: 'this.text.length<=?0'}")
    Post findTopByIdNotInAndQueryOrderByMarkDesc(List<String> ids, Integer size);

    List<Post> findTop10ByOrderByMarkDesc();
    List<Post> findTop10ByOrderByMark2Desc();

    Post getPostById(String postId);
}
