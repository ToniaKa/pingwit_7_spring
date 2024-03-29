package pl.pingwit.springdemo.controller.joke;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pingwit.springdemo.service.joke.JokeService;

import java.util.List;

@RestController
@RequestMapping("/joke")
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public List<JokeDto> getJoke(@RequestParam(name = "count", required = false) Integer count) {
        return jokeService.getJoke(count);
    }
}
