package library.exception;

public class UserCannotBorrowBookException extends RuntimeException{

    public UserCannotBorrowBookException(String message){
        super(message);
    }
}
