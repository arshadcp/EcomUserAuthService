package dev.Arshad.EcomUserAuthService.Exception;

public class invalidSessionException extends RuntimeException{
    public invalidSessionException(String message) {
        super(message);
    }
}
