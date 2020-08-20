package com.todo.controller;

import com.todo.entity.Todo;
import com.todo.entity.TodoRepository;
import com.todo.entity.User;
import com.todo.entity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("userId") String userId){
        Optional<User> user= userRepository.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userId}/todos")
    public ResponseEntity<List<Todo>> getAllTasksByUser(@PathVariable("userId") String userId) {
        LOGGER.info("Get all called");
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
            Iterable<Todo> result = todoRepository.findAll();
            List<Todo> todoList = new ArrayList<>();
            result.forEach(todoList::add);
            return new ResponseEntity<>(todoList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/")
    public ResponseEntity<String> createTodo(@RequestBody User user){
        LOGGER.info("Post user called");
        User result=userRepository.save(user);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
