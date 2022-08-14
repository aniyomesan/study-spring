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

    public ToDoItem createItem(ToDoItem item) {
        toDoItemRepository.createItem(item);
        return item;
    }

    public ToDoItem updateItem(ToDoItem item) {
        toDoItemRepository.updateItem(item);
        return item;
    }

    public void deleteItem(int id) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setId(id);
        toDoItemRepository.deleteItem(toDoItem);
    }
}
