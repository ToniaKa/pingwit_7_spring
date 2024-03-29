package pl.pingwit.springdemo.controller.joke;

public class JokeDto {
    private final String setup;

    private final String punchline;

    public JokeDto(String setup, String punchline) {
        this.setup = setup;
        this.punchline = punchline;

    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }
}
