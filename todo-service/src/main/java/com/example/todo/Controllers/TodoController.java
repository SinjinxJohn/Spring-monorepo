package com.example.todo.Controllers;

import com.example.todo.DTO.TodoRequest;
import com.example.todo.DTO.TodoResponse;
import com.example.todo.Service.TodoServiceImp.TodoServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TodoController {
    private final TodoServiceImplementation todoServiceImplementation;

    @PostMapping("/create-tasks")
    public ResponseEntity<String> createTodo(@Valid @RequestBody TodoRequest requestBody){
        return ResponseEntity.ok(todoServiceImplementation.createTodo(requestBody));

    }

    @GetMapping("/get-all-tasks")
    public ResponseEntity<List<TodoResponse>> getAllTodo(){
        return ResponseEntity.ok(todoServiceImplementation.getAllTodo());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody TodoRequest requestBody){
        return ResponseEntity.ok(todoServiceImplementation.updateTask(id,requestBody));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id){
        return ResponseEntity.ok(todoServiceImplementation.deleteTask(id));
    }

    @GetMapping("/get-paginated-tasks")
    public ResponseEntity<Page<TodoResponse>> getPaginatedTodo(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "5") int size,
                                                               @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "asc") String direction){
        return ResponseEntity.ok(todoServiceImplementation.getTodoSortByDate(page,size,sortBy,direction));
    }

    @GetMapping("/get-count-tasks")
    public ResponseEntity<Integer> getCountTasks(){
        return ResponseEntity.ok(todoServiceImplementation.getCountOfTodos());
    }
}
