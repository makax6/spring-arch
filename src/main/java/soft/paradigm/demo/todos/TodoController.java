package soft.paradigm.demo.todos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/todos")
@CrossOrigin
@Tag(name = "todo", description = "todo apis")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Operation(summary = "create todo")
    @PostMapping("")
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo result = todoService.createTodo(todo);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all todos")
    @GetMapping("")
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @Operation(summary = "get todos paginated")
    @GetMapping("/paginated")
    public Page<Todo> getTodosPaginated(@PageableDefault(page = 0, size = 20)
                                        @SortDefault(sort = "createdDate", direction = Sort.Direction.ASC) Pageable p) {
        return todoService.getTodosPaginated(p);
    }

    @Operation(summary = "get todo by id")
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getSingleTodo(@PathVariable("id") String id) {
        Todo result = todoService.getSingleTodo(id);
        return new ResponseEntity<>(result, HttpStatus.OK);


    }

    @Operation(summary = "delete todo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        todoService.deleteTodoById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Successfully deleted ");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "update todo")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Todo todo) {
        Todo result = todoService.updateTodo(id, todo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}