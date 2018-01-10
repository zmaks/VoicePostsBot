package com.zheltoukhov;

import com.zheltoukhov.joker.repository.PostRepository;
import com.zheltoukhov.joker.service.PostService;
import com.zheltoukhov.joker.telegram.JokerBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MongoPostsApplication implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private JokerBot jokerBot;

    @PostConstruct
    void init() {

    }


	public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(MongoPostsApplication.class, args);
	}

	@Autowired
    PostRepository postRepository;

    @Override
    public void run(String... strings) throws Exception {
	    postService.preparePosts();

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("lol");

        postRepository.findTop10ByOrderByMarkDesc().stream()
                .map(x->x.getNum()+"\n"+ x.getMark()+ ": likes: "+x.getLikes()+" views: "+x.getViews()+"\n"+x.getText())
                .forEach(System.out::println);
        System.out.println("\n\n");

        postRepository.findTop10ByOrderByMark2Desc().stream()
                .map(x-> x.getNum()+"\n"+x.getMark2()+ ": likes: "+x.getLikes()+" views: "+x.getViews()+"\n"+x.getText())
                .forEach(System.out::println);

        /*TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(jokerBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }*/

        /*postRepository.deleteAll();
        List<Post> posts = postRepository.save(new VkApi().getPosts());

        int startInd = 0;
        int endInd = 300;
        int plusAmount = 150;
        List<Post> curPosts;
        while (endInd<posts.size()) {
            curPosts = posts.subList(startInd, endInd);
            startInd+=plusAmount;
            endInd+=plusAmount;
            float avg = (float) curPosts.stream().mapToInt(Post::getLikes).average().orElse(0);

            for (Post post : curPosts) {
                if (post.getVoiceText() != null && !post.getVoiceText().isEmpty() && post.getLikes() > 50) {
                    float curLikes = (float) post.getLikes();
                    float mark = (curLikes - avg) / avg;
                    if (post.getMark() != null && post.getMark() != 0) {
                        post.setMark((post.getMark()+mark)/2);
                    } else
                        post.setMark(mark);
                }
            }
            postRepository.save(curPosts);
        }
*/
    }
}
