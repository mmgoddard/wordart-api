package nz.net.example.wordart.gen.exception;

public class WAInternalServiceException extends RuntimeException {

    private static final String ERROR_RESPONSE_MESSAGE = "Internal Service Error";

    public WAInternalServiceException() {
        super(ERROR_RESPONSE_MESSAGE);
    }

    public WAInternalServiceException(String message, Exception e) {
        super(message, e);
    }
}
