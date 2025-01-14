package dev.Arshad.EcomUserAuthService.Exception;

public class invalidCredentialException extends RuntimeException{
    public invalidCredentialException(String message) {
        super(message);
    }
}
