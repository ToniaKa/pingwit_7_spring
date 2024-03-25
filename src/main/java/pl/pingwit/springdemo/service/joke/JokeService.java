package pl.pingwit.springdemo.service.joke;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JokeService {
    private final String url;

    private final RestTemplate restTemplate;

    public JokeService(@Value("${pingwit.joke.url}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public String getRandomJoke(Integer count) {

        JokeApiResponce responce = restTemplate.getForObject(url, JokeApiResponce.class);

        if (count > 5) {
            List<String> jokeList = new ArrayList<>();
            jokeList.add(responce.getSetup());
            jokeList.add(responce.getSetup());
            jokeList.add(responce.getSetup());
            jokeList.add(responce.getSetup());
            jokeList.add(responce.getSetup());
            return jokeList.toString();

        }
        return responce.getSetup();
    }

    public String getJoke() {
        JokeApiResponce responce = restTemplate.getForObject(url, JokeApiResponce.class);
        return responce.getSetup();
    }
}



