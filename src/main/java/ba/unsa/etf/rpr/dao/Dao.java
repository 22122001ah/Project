package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

/**
 * Root interface for all DAO classes
 *
 * @author Dino Keco
 */
public interface Dao<T> {

    /**
     * get entity from database base on ID
     * @param id primary key of entity
     * @return Entity from database
     */
    T getById(int id) throws PlaysException;


    /**
     * Lists all entities from database. WARNING: Very slow operation because it reads all records.
     * @return List of entities from database
     */
    List<T> getAll() throws PlaysException;
    T add( T item) throws PlaysException;

    T update(T item) throws PlaysException;
}