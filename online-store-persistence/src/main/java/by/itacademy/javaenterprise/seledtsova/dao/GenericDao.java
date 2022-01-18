package by.itacademy.javaenterprise.seledtsova.dao;

import java.util.List;

public interface GenericDao<I, T> {

    void add(T entity);

    void delete(T entity);

    void merge(T entity);

    T findById(I id);

    List<T> findAll();

    List<T> findWithPagination(int pageNumber, int pageSize);

    Long getCount();
}
