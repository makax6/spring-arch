package soft.paradigm.demo.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDetails {
    private String message;
    private String uri;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;


    public ErrorDetails(String message, String uri) {
        this();
        this.message = message;
        this.uri = uri;
    }

    public ErrorDetails() {
        this.timestamp = LocalDateTime.now();
    }
}