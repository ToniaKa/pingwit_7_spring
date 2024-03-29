package pl.pingwit.springdemo.service.joke;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pingwit.springdemo.controller.joke.JokeDto;

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

    public List<JokeDto> getJoke(Integer count) {
        if (count == null || count == 0) {
            JokeApiResponce apiResponce = restTemplate.getForObject(url, JokeApiResponce.class);
            String setup = apiResponce.getSetup();
            String punchline = apiResponce.getPunchline();
            return List.of(new JokeDto(setup, punchline));
        }
        if (count > 0 && count <= 5) {
            List<JokeDto> jokeList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                JokeApiResponce apiResponce = restTemplate.getForObject(url, JokeApiResponce.class);
                String setup = apiResponce.getSetup();
                String punchline = apiResponce.getPunchline();
                jokeList.add(new JokeDto(setup, punchline));
            }
            return jokeList;
        }
        List<JokeDto> jokeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JokeApiResponce apiResponce = restTemplate.getForObject(url, JokeApiResponce.class);
            String setup = apiResponce.getSetup();
            String punchline = apiResponce.getPunchline();
            jokeList.add(new JokeDto(setup, punchline));
        }
        return jokeList;
    }

}



