package ru.makar.todoapi.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.makar.todoapi.model.Todo;
import ru.makar.todoapi.repository.TodoRepository;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner{

    private final TodoRepository repository;

    @Autowired
    public DataInitializer(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Initializing sample data");
        repository.save(new Todo("Task 1", false));
        repository.save(new Todo("Task 2", true));
        repository.save(new Todo("Task 3", false));
        repository.save(new Todo("Task 4", true));
        repository.save(new Todo("Task 5", false));
    }
}
