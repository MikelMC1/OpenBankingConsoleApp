package excpetion;

public class AuthenticationException extends Exception{
    private String message;

    public AuthenticationException(String message){
        this.message = message;
    }


}

