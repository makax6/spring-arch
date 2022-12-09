package soft.paradigm.demo.error;

import org.springframework.http.HttpStatus;

public abstract class BaseExceptionHandler extends RuntimeException {
    public BaseExceptionHandler(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}