package com.bkap.dao;

import com.bkap.entities.Product;

import java.util.List;

// Má mày lười vừa thôi code điiii

public interface GeneralDao<T> {
    public List<T> get();
    public List<T> getByName(String name);
    public T findId(Object id);
    public boolean add(T entity);
    public boolean edit(T entity);
    public boolean remove(T entity);
    public List<String> listId();
    public List<Product> checkForeignKey(String checkId);
}
