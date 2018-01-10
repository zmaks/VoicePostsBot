package com.zheltoukhov.joker;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallpostFull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim on 21.10.2017.
 */
@Component
public class VkApi {
    private static final String SERVICE_KEY = "abb2a3ecabb2a3ecab7134f0b2abe9abe0aabb2abb2a3ecf3511591cb6723e318a2dae0";
    private static final Integer APP_ID = 5965836;
    private static final Integer OWNER_ID = -45491419;
    private static final Integer POSTS_AMOUNT = 14000;



    private ServiceActor actor;
    private TransportClient transportClient;
    private VkApiClient vk;

    @PostConstruct
    void init() {
        actor =  new ServiceActor(APP_ID, SERVICE_KEY);
        transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
    }

    /*public VkApi() throws ClientException, ApiException {
        actor =  new ServiceActor(APP_ID, SERVICE_KEY);
        transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
    }*/

    public List<WallpostFull> getPosts() throws JokeException {
        List<WallpostFull> posts = new ArrayList<>();
        int startOffset = 50;
        int plusOffset = 100;

        try {

            for (int curOffset = startOffset; curOffset <= POSTS_AMOUNT; curOffset += plusOffset) {
                posts.addAll(
                    vk.wall()
                            .get(actor)
                            .ownerId(OWNER_ID)
                            .count(plusOffset)
                            .offset(curOffset)
                            .execute()
                            .getItems()
                );
                Thread.sleep(500L);
            }
        } catch (InterruptedException | ClientException | ApiException e) {
            throw new JokeException(e.getMessage(), e);
        }

        return posts;

    }
}
