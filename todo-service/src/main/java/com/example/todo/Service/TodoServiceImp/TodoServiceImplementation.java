package com.example.todo.Service.TodoServiceImp;

import com.example.todo.CustomExceptions.ResourceNotFoundException;
import com.example.todo.DTO.TodoRequest;
import com.example.todo.DTO.TodoResponse;
import com.example.todo.Entities.Todo;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Service.TodoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImplementation implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public String createTodo(TodoRequest todoRequest) {
        if(todoRepository.existsByTitle(todoRequest.getTitle())){
            throw new IllegalArgumentException("Category already exists");
        }
        Todo todo = Todo.builder().title(todoRequest.getTitle()).description(todoRequest.getDescription()).build();
        todoRepository.save(todo);

        return "Task created successfully";
    }

    @Override
    public List<TodoResponse> getAllTodo() {
        return todoRepository.findAll().stream()
                .map(todo->TodoResponse.builder()
                        .ID(todo.getID())
                        .title(todo.getTitle())
                        .description(todo.getDescription())
                        .createdAt(todo.getCreatedAt())
                        .updatedAt(todo.getUpdatedAt())
                        .build())
                .toList();
    }

    @Override
    public String updateTask(int ID,TodoRequest todoRequest) {
        int result = todoRepository.updateTaskById(ID,todoRequest.getDescription(),todoRequest.getTitle());
        if (result == 0){
            throw new ResourceNotFoundException("No task with this title exists");
        }
        return "task updated successfully";
    }

    @Override
    @Transactional
    public String deleteTask(int ID) {
        int result = todoRepository.deleteByID(ID);
        if(result==0){
            throw new ResourceNotFoundException("No task exists with ID:"+ID+" exists");
        }
        return "Task deleted successfully";

    }

    @Override
    public Page<TodoResponse> getTodoSortByDate(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Todo> todoPage = todoRepository.findAll(pageable);
        List<TodoResponse> responses = todoPage.getContent().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(responses,pageable,todoPage.getTotalElements());
    }

    @Override
    public int getCountOfTodos() {
        List<Todo> result = todoRepository.findAll();
        if(result.isEmpty()){
            throw new ResourceNotFoundException("No tasks exists");
        }
        return result.size();
    }

    private TodoResponse convertToResponse(Todo todo) {
        TodoResponse response = new TodoResponse();
        response.setID(todo.getID());
        response.setTitle(todo.getTitle());
        response.setDescription(todo.getDescription());
        response.setCreatedAt(todo.getCreatedAt());
        response.setUpdatedAt(todo.getUpdatedAt());
        return response;
    }
}
