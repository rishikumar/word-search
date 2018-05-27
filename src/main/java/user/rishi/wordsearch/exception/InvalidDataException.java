package user.rishi.wordsearch.exception;

/*
 *  Custom exception used to raise errors for bad input
 */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
