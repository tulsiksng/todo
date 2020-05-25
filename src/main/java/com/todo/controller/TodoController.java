package com.todo.controller;

import com.todo.entity.Todo;
import com.todo.entity.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("todos")
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoRepository todoRepository;


    @GetMapping("/")
    public ResponseEntity<List<Todo> > getAllTasks() {
        LOGGER.info("Get all called");
        Iterable<Todo> result = todoRepository.findAll();
        List<Todo> todoList = new ArrayList<>();
        result.forEach(todoList::add);
        return new ResponseEntity<>(todoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Todo>> getTaskById(@PathVariable("id") String id){
        LOGGER.info("Get by id called" + id);
        Optional<Todo> todo=todoRepository.findById(id);
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        LOGGER.info("Post todo called");
        Todo result=todoRepository.save(todo);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable("id") String id){
        LOGGER.info("Delete by id called"+id);
        boolean result= todoRepository.existsById(id);
        if(result){
            todoRepository.deleteById(id);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Todo>> modifyTaskOfTodo(@PathVariable("id") String id, @RequestBody Todo todo){
        LOGGER.info("Modify called "+ id);
        Optional<Todo> result= todoRepository.findById(id);
        if(result.isPresent()){
            Todo existTodo= result.get();
            existTodo.setTasks(todo.getTasks());
            todoRepository.save(existTodo);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }



}
