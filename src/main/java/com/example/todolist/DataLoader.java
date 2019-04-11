package com.example.todolist;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    TodoRepository repository;

    @Override
    public void run(String... strings) throws Exception{
        Todo todo= new Todo("Drawing ","Drawing pictures with my kids and coloring");
        repository.save(todo);

        todo=new Todo("Study","studying Java");
        repository.save(todo);

        todo=new Todo("Job searching","");
        repository.save(todo);
    }
}

