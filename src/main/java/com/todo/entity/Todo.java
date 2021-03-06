package com.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@UserDefinedType
@Table
public class Todo {

    @Id
    @PrimaryKey
    private String id;

    private List<String> tasks;

}
