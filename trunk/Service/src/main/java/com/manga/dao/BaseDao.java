package com.manga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@Repository
@Transactional
public abstract class BaseDao<T,ID> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    private Class<T> persistentClass;

    public BaseDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(persistentClass);
    }

    public T findById(ID id) {
        return mongoTemplate.findById(id,persistentClass);
    }

    public void insert(T document) {
        mongoTemplate.insert(document);
    }

    public void remove(T document) {
        mongoTemplate.remove(document);
    }

}
