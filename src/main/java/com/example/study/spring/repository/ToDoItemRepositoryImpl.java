package com.example.study.spring.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.spring.model.ToDoItem;

@Repository
@Transactional
public class ToDoItemRepositoryImpl implements ToDoItemRepository {

    @Override
    public List<ToDoItem> findAllItems() throws DataAccessException {
        return null;
    }

    @Override
    public ToDoItem findItem(int id) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ToDoItem findItemForUpdate(int id) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createItem(ToDoItem item) throws DataAccessException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateItem(ToDoItem item) throws DataAccessException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteItem(ToDoItem item) throws DataAccessException {
        // TODO Auto-generated method stub
        
    }   
}
