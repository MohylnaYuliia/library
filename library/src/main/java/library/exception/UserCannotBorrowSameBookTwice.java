package library.exception;

public class UserCannotBorrowSameBookTwice extends RuntimeException {

    public UserCannotBorrowSameBookTwice(String message) {
        super(message);
    }
}
