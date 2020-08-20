package com.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Getter@Setter
@AllArgsConstructor
@Table
public class User {

    @PrimaryKey
    @Id
    private String phone;
    private List<Todo> todoList;
}
