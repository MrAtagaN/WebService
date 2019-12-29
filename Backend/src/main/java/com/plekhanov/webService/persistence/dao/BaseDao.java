package com.plekhanov.webService.persistence.dao;

public interface BaseDao<T, ID> {

    T findById(ID id);

    T findByIdForUpdate(ID id);

    ID saveOrUpdate(T t);

    //T save(T t);

    void delete(ID id);
}
