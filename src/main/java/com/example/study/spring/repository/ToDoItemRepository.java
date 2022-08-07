package com.example.study.spring.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.lang.Nullable;

import com.example.study.spring.model.ToDoItem;

public interface ToDoItemRepository {

    /**
     * Finds and returns all ToDo items.
     *
     * @return ToDo items
     * @throws DataAccessException thrown if any I/O error occurred.
     */
    List<ToDoItem> findAllItems() throws DataAccessException;

    /**
     * Finds and returns a ToDo item specified by ID.
     *
     * @return ToDo item ({@code null} if not found)
     * @throws DataAccessException thrown if any I/O error occurred.
     */
    @Nullable
    ToDoItem findItem(int id) throws DataAccessException;

    /**
     * Finds and returns a ToDo item specified by ID, with an exclusive lock.
     *
     * @return ToDo item ({@code null} if not found)
     * @throws DataAccessException thrown if any I/O error occurred.
     */
    @Nullable
    ToDoItem findItemForUpdate(int id) throws DataAccessException;

    /**
     * Creates a new ToDo item.
     *
     * <p>
     * {@code id} is generated and filled in the argument object.
     * </p>
     *
     * @param item new ToDo item
     * @throws DataAccessException thrown if any I/O error occurred.
     */
    void createItem(ToDoItem item) throws DataAccessException;

    /**
     * Updates a ToDo item.
     *
     * @param item ToDo item to update
     * @throws DataAccessException thrown if any I/O error occurred.
     */
    void updateItem(ToDoItem item) throws DataAccessException;

    /**
     * Deletes a ToDo item.
     *
     * @param item ToDo item to update
     * @throws DataAccessException thrown if any I/O error occurred.
     */
    void deleteItem(ToDoItem item) throws DataAccessException;
}
