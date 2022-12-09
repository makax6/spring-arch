package soft.paradigm.demo.error;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseExceptionHandler {
    public ConflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}