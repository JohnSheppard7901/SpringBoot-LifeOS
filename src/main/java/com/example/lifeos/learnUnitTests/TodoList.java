package com.example.lifeos.learnUnitTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoList {
    private final List<TodoSimple> todos= new ArrayList<>();


    public List<TodoSimple> getAllTodos() {
        return new ArrayList<>(todos);
    }

    public TodoSimple createTodo(String title) {
        TodoSimple todo = new TodoSimple(title);
        todos.add(todo);
        return todo;
    }

    public Optional<TodoSimple> getTodoById(String id) {
        return todos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public boolean updateTodo(String id, String newTitle, Boolean completed) {
        Optional<TodoSimple> todoOpt = getTodoById(id);
        if (todoOpt.isPresent()) {
            TodoSimple todo = todoOpt.get();
            if (newTitle != null) {
                todo.setTitle(newTitle);
            }
            if (completed != null) {
                todo.setCompleted(completed);
            }
            return true;
        }
        return false;
    }

    public boolean deleteTodo(String id) {
        return todos.removeIf(t -> t.getId().equals(id));
    }

}
