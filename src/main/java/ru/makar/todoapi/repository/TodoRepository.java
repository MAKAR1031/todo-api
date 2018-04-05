package ru.makar.todoapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.makar.todoapi.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    Todo findByText(String text);
}
