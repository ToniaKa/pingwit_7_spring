package pl.pingwit.springdemo.controller.joke;

public class JokeDto {
    private final String joke;

    public String getJoke() {
        return joke;
    }

    public JokeDto(String setup) {
        this.joke = setup;
    }
}
