package com.example.lifeos.learnUnitTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class TodoListTest {
    private TodoList todoList;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
    }

    @Test
    void testCreateTodo() {
        TodoSimple todo = todoList.createTodo("Feed Max");
        assertNotNull(todo.getId());
        assertEquals("Feed Max", todo.getTitle());
        assertFalse(todo.isCompleted());
        assertEquals(1, todoList.getAllTodos().size());
    }

    @Test
    void testGetTodoById() {
        TodoSimple todo = todoList.createTodo("Feed Nugget");
        Optional<TodoSimple> found = todoList.getTodoById(todo.getId());
        assertTrue(found.isPresent());
        assertEquals("Feed Nugget", found.get().getTitle());
    }

    @Test
    void testGetTodoById_NotFound() {
        Optional<TodoSimple> found = todoList.getTodoById("non-existend-id");
        assertTrue(found.isEmpty());
    }

    @Test
    void testUpdateTodo() {
        TodoSimple todo = todoList.createTodo("Old");
        boolean updated = todoList.updateTodo(todo.getId(), "New", true);

        assertTrue(updated);
        Optional<TodoSimple> updatedTodo = todoList.getTodoById(todo.getId());
        assertTrue(updatedTodo.isPresent());
        assertEquals("New", updatedTodo.get().getTitle());
        assertTrue(updatedTodo.get().isCompleted());
    }

    @Test
    void testUpdateTodo_NotFound() {
        boolean updated = todoList.updateTodo("nicht-existente-id", "Neu", true);
        assertFalse(updated);
    }

    @Test
    void testDeleteTodo() {
        TodoSimple todo = todoList.createTodo("Zu löschen");
        boolean deleted = todoList.deleteTodo(todo.getId());

        assertTrue(deleted);
        assertTrue(todoList.getAllTodos().isEmpty());
    }

    @Test
    void testDeleteTodo_NotFound() {
        boolean deleted = todoList.deleteTodo("nicht-existente-id");
        assertFalse(deleted);
    }

    @Test
    void testGetAllTodos_ReturnsCopy() {
        todoList.createTodo("Task 1");
        todoList.createTodo("Task 2");

        var todos = todoList.getAllTodos();
        assertEquals(2, todos.size());

        todos.clear();
        assertEquals(2, todoList.getAllTodos().size());
    }

}
