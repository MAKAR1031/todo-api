package ru.makar.todoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String text;

    private boolean priority;

    public Todo(String text, boolean priority) {
        this.text = text;
        this.priority = priority;
    }

    public void togglePriority() {
        priority = !priority;
    }
}
