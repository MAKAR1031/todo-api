package ru.makar.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.makar.todoapi.model.Todo;
import ru.makar.todoapi.repository.TodoRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@Transactional
public class TodoController {
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_ERROR = "error";
    private static final String TODO_NOT_FOUND_MESSAGE = "Todo not found";
    private static final String TODO_NOT_UNIQUE_MESSAGE = "You have already added such a todo!";

    private final TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping
    @ResponseBody
    public Iterable<Todo> all() {
        return repository.findAll();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity add(@RequestBody Todo todo) {
        Todo old = repository.findByText(todo.getText());
        if (old != null) {
            return ResponseEntity.ok(singleValueMap(KEY_ERROR, TODO_NOT_UNIQUE_MESSAGE));
        }
        repository.save(todo);
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(path = "/priority", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity changePriority(@RequestBody Map<String, String> body) {
        String id = body.get("id");
        if (id == null) {
            return ResponseEntity.ok(singleValueMap(KEY_ERROR, TODO_NOT_FOUND_MESSAGE));
        }
        Todo todo = repository.findById(Long.parseLong(id)).orElse(null);
        if (todo == null) {
            return ResponseEntity.ok(singleValueMap(KEY_ERROR, TODO_NOT_FOUND_MESSAGE));
        }
        todo.togglePriority();
        repository.save(todo);
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok(singleValueMap(KEY_SUCCESS, true));
    }

    private <T> Map<String, T> singleValueMap(String key, T value) {
        HashMap<String, T> result = new HashMap<>();
        result.put(key, value);
        return result;
    }
}
