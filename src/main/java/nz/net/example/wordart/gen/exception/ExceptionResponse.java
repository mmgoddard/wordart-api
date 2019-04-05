package nz.net.example.wordart.gen.exception;

import java.util.Collections;
import java.util.List;

public class ExceptionResponse {

    private int code;
    private List<String> messages;

    ExceptionResponse(int code, String message) {
        this.code = code;
        this.messages = Collections.singletonList(message);
    }

    public int getCode() {
        return code;
    }

    public List<String> getMessages() {
        return messages;
    }
}