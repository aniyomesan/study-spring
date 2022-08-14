package com.example.study.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.spring.exception.BadRequestException;
import com.example.study.spring.exception.NotFoundException;
import com.example.study.spring.model.ToDoItem;
import com.example.study.spring.service.ToDoItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ToDoItemRestController {

    private final ToDoItemService toDoItemService;

    @GetMapping("/todos")
    public List<ToDoItem> findAllItems() {
        return toDoItemService.findAlItems();
    }

    @GetMapping("/todos/{id}")
    public ToDoItem findItem(@PathVariable int id) {
        ToDoItem toDoItem = toDoItemService.findItem(id);
        if (toDoItem == null) {
            throw new NotFoundException("Not Found.");
        }
        return toDoItem;
    }

    @PostMapping("/todos")
    public ToDoItem createItem(@RequestBody @Validated ToDoItem item) {
        return toDoItemService.createItem(item);
    }

    @PutMapping("/todos/{id}")
    public ToDoItem updateItem(@PathVariable int id, @RequestBody @Validated ToDoItem item) {
        if (id != item.getId()) {
            throw new BadRequestException("Bad Request.");
        }
        if (toDoItemService.findItemForUpdate(id) == null) {
            throw new NotFoundException("Not Found.");
        }
        ToDoItem toDoItem = toDoItemService.updateItem(item);
        return toDoItem;
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable int id) {
        if (toDoItemService.findItemForUpdate(id) == null) {
            throw new NotFoundException("Not Found.");
        }
        toDoItemService.deleteItem(id);
    }
}
