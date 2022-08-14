package com.example.study.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.study.spring.model.ToDoItem;
import com.example.study.spring.repository.ToDoItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    public List<ToDoItem> findAlItems() {
        return toDoItemRepository.findAllItems();
    }

    public ToDoItem findItem(int id) {
        return toDoItemRepository.findItem(id);
    }

    public ToDoItem findItemForUodate(int id) {
        return toDoItemRepository.findItemForUpdate(id);
    }

    public void createItem(ToDoItem item) {
        toDoItemRepository.createItem(item);
    }

    public void updateItem(ToDoItem item) {
        toDoItemRepository.updateItem(item);
    }

    public void deleteItem(ToDoItem item) {
        toDoItemRepository.deleteItem(item);
    }
}
