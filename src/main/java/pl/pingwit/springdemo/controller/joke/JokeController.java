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


    @GetMapping("/{count}") // этот эндпоинт сделай плиз через @RequestParam, а не @PathVariable
    // также в jokeService оставь один метод, который принимает Integer. Если этот Integer = null, то возвращай 1 шутку,
    // если от 1 до 5 - от 1 до 5 разных шуток, если count > 5 - сервис вернет 5 разных шуток
    public JokeDto getJoke(@PathVariable(name = "count") Integer count) {
        return new JokeDto(jokeService.getRandomJoke(count));
    }

    @GetMapping // если выполнишь комент из строки 20, то этот эндпоинт удали
    public JokeDto getJoke() {
        return new JokeDto(jokeService.getJoke());
    }
}
