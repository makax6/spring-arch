package soft.paradigm.demo.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft.paradigm.demo.error.ConflictException;
import soft.paradigm.demo.error.NotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepo;


    public List<Todo> getAllTodos() {
        return todoRepo.findAll();

    }

    public Page<Todo> getTodosPaginated(
            Pageable p) {
        try {
            return todoRepo.findAll(p);
        } catch (Exception c) {
            throw new InternalError();
        }


    }

    public Todo getSingleTodo(String id) {
        try {
            return todoRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No record was found");
        }
    }


    public Todo createTodo(Todo todo) {
        if (todoRepo.findByTodo(todo.getTodo()) != null) {
            throw new ConflictException("Todo already exists");
        }
        return todoRepo.save(todo);
    }


    public Todo updateTodo(String id, Todo todo) {
        Optional<Todo> todoWithId = todoRepo.findById(id);
        if (!todoWithId.isPresent()) throw new NotFoundException("No record was found ");
        Optional<Todo> todoWithPropertyTodo = Optional.ofNullable(todoRepo.findByTodo(todo.getTodo()));
        if (todoWithPropertyTodo.isPresent() && !todoWithPropertyTodo.get().getId().equals(id))
            throw new ConflictException("Todo already exists");
        Todo todoToUpdate = todoWithId.get();
        todoToUpdate.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToUpdate.getTodo());
        todoToUpdate.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToUpdate.getDescription());
        todoToUpdate.setIsCompleted(todo.getIsCompleted() != null ? todo.getIsCompleted() : todoToUpdate.getIsCompleted());
        return todoRepo.save(todoToUpdate);

    }

    public void deleteTodoById(String id) {
        Optional<Todo> todo = todoRepo.findById(id);
        if (!todo.isPresent()) throw new NotFoundException("No record was found ");
        todoRepo.deleteById(id);
    }
}