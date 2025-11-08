package com.example.todo.Service;

import com.example.todo.DTO.TodoRequest;
import com.example.todo.DTO.TodoResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoService {
    String createTodo(TodoRequest todoRequest);
    List<TodoResponse> getAllTodo();
    String updateTask(int ID,TodoRequest todoRequest);
    String deleteTask(int ID);
    Page<TodoResponse> getTodoSortByDate(int page, int size, String sortBy, String direction);
    int getCountOfTodos();


}