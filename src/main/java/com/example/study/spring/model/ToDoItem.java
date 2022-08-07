package com.example.study.spring.model;

import lombok.Data;

@Data
public class ToDoItem {

    private int id;

    private String title;

    private boolean done;
}
