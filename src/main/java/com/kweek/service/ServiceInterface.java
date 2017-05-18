package com.kweek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by Harrison on 2017-01-23.
 */

public interface ServiceInterface<T> {

    T save(T t);

    T find(long id);

    List<T> findAll();

    Boolean update(T t);

    void delete(long id);

    Boolean delete(T t);

    Page<T> findAll(PageRequest pageRequest);

    Page<T> findByParam(String param, PageRequest pageRequest);
}
