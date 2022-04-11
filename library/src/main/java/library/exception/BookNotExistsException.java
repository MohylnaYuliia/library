package library.exception;

public class BookNotExistsException extends RuntimeException {

    public BookNotExistsException(String message) {
        super(message);
    }
}
