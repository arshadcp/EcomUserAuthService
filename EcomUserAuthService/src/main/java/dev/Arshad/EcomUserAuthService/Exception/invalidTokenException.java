package dev.Arshad.EcomUserAuthService.Exception;

public class invalidTokenException extends RuntimeException{
    public invalidTokenException(String message) {
        super(message);
    }
}
