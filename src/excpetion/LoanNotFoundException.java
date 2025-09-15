package excpetion;

public class LoanNotFoundException extends Exception {

    private String message;

    public LoanNotFoundException(String message){
        this.message = message;
    }
}
