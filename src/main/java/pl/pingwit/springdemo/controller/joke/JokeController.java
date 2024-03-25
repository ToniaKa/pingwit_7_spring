package pl.pingwit.springdemo.controller.joke;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pingwit.springdemo.service.joke.JokeService;

@RestController
@RequestMapping("/joke")
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }


    @GetMapping("/{count}")
    public JokeDto getJoke(@PathVariable(name = "count") Integer count) {
        return new JokeDto(jokeService.getRandomJoke(count));
    }

    @GetMapping
    public JokeDto getJoke() {
        return new JokeDto(jokeService.getJoke());
    }
}
