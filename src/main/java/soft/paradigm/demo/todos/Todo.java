package soft.paradigm.demo.todos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import soft.paradigm.demo.auditable.AuditMetadata;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class Todo extends AuditMetadata {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    private String id;

    @NotBlank(message = "todo cannot be null")
    private String todo;

    @NotBlank(message = "description cannot be null")
    private String description;

    private Boolean isCompleted;


}