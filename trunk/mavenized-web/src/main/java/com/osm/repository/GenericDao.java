package com.osm.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    T get(ID key);
    
    void save(T entity);
    
    void update(T entity);
    
    void delete(ID key);
    
    List<T> findAll();
}
