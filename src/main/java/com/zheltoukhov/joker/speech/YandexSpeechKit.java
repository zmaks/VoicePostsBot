package com.zheltoukhov.joker.speech;

import com.zheltoukhov.joker.speech.params.VoiceParameters;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Maksim on 31.10.2017.
 */
@Component
public class YandexSpeechKit implements VoiceSynthesis {
    private volatile HttpClient httpClient;
    private static final String REQUEST_TEMPLATE = "https://tts.voicetech.yandex.net/generate?key=%s&format=opus%s";

    @Value("${yandex.api-key}")
    private String key;

    @PostConstruct
    private void init() {
        httpClient = HttpClientBuilder.create().setMaxConnTotal(100).build();
    }

    public InputStream getVoice(VoiceParameters parameters) throws VoiceException {

        String url = getUrlByParams(parameters);

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("accept","audio/mpeg");
        HttpResponse response;
        InputStream result = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() > 200)
                throw new VoiceException("Bad response from Yandex: "
                        + response.getStatusLine().getStatusCode()
                        + response.getStatusLine().getReasonPhrase()
                );
            result = response.getEntity().getContent();
        } catch (IOException e) {
            throw new VoiceException(e.getMessage(), e);
        }

        return result;

    }

    protected String getUrlByParams(VoiceParameters parameters) {
        return String.format(REQUEST_TEMPLATE, key, parameters.getHttpParamsString());
    }
}
