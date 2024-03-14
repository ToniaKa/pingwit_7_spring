package pl.pingwit.springdemo.exception;

public class PingwitNotFoundException extends RuntimeException{
    public PingwitNotFoundException(String message) {
        super(message);
    }
}
