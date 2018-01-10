package com.zheltoukhov.joker.service;

import com.vk.api.sdk.objects.wall.WallpostFull;
import com.zheltoukhov.joker.JokeException;
import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.VkApi;
import com.zheltoukhov.joker.entity.Post;
import com.zheltoukhov.joker.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Maksim on 22.10.2017.
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private VkApi vkApi;

    @Value("${posts.load}")
    private Boolean load;

    private List<Post> loadPosts() throws JokeException {
        List<Post> posts = vkApi.getPosts()
                .stream()
                .filter(this::wallPostFilterPredicate)
                .map(p -> new Post(0, p.getId(), p.getText(), p.getLikes().getCount(), p.getViews().getCount()))
                .collect(Collectors.toList());
        int n = 0;
        for (Post post : posts)
            post.setNum(++n);
        return postRepository.save(posts);
    }

    public void preparePosts() throws JokeException {
        if (!load) return;
        postRepository.deleteAll();
        List<Post> posts =  loadPosts();

        int startInd = 0;
        int endInd = 300;
        int plusAmount = endInd/2;

        List<Post> curPosts;
        while (endInd<posts.size()) {
            curPosts = posts.subList(startInd, endInd);
            startInd+=plusAmount;
            endInd+=plusAmount;
            float avgLikes = (float) curPosts.stream().mapToInt(Post::getLikes).average().orElse(0);
            float avgViews = (float) curPosts.stream().mapToInt(Post::getViews).average().orElse(0);

            for (Post post : curPosts) {
                float curLikes = (float) post.getLikes();
                float curViews = (float) post.getViews();
                float likesMark = (curLikes - avgLikes) / avgLikes;
                float viewsMark = curViews == 0 ? -1 : curLikes/curViews;
                if (post.getMark() != null && post.getMark() != 0) {
                    post.setMark((post.getMark()+likesMark)/2f);
                } else
                    post.setMark(likesMark);
                post.setMark2(viewsMark);
            }
            postRepository.save(curPosts);
        }
    }

    public Post getNotViewedPost(List<String> viewedPostIds) throws JokeException {
       List<Post> posts = postRepository.findTop2ByIdNotInOrderByMarkDesc(viewedPostIds);
       posts.addAll(postRepository.findTop3ByIdNotInOrderByMark2Desc(viewedPostIds));
       if (posts.isEmpty()) throw new JokeException(MessageConstants.NO_JOKES);
       return posts.get(new Random().nextInt(posts.size()));
    }

    /*public Post getNotViewedPost(List<String> viewedPostIds, Integer length) {
        return length == null ? getNotViewedPost(viewedPostIds):
                postRepository.findTopByIdNotInAndTextLessThanOrderByMarkDesc(viewedPostIds, length);
    }*/

    private boolean wallPostFilterPredicate(WallpostFull post) {
        return
                post.getText() != null && !post.getText().isEmpty() &&
                post.getLikes() != null && post.getLikes().getCount() > 50 &&
                post.getViews() != null && post.getViews().getCount() > post.getLikes().getCount() &&
                post.getText().length() <= 1000;
    }

    public Post getPostById(String postId) throws JokeException {
        Post post = postRepository.getPostById(postId);
        if (post == null) throw new JokeException(MessageConstants.NO_SUCH_JOKE_BY_ID);
        return post;
    }
}
