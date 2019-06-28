package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface BaseRepository<T> {

    public List<T> findAll();

    public List<T> findAll(Sort sort);

    public List<T> findAllById(Iterable<String> ids);

    
    public <S extends T> List<S> saveAll(Iterable<S> entities);

    
    public void flush();

    
    public <S extends T> S saveAndFlush(S entity);

    
    public void deleteInBatch(Iterable<T> entities);

    
    public void deleteAllInBatch();

    
    public T getOne(String id);

    
    public <S extends T> List<S> findAll(Example<S> example);

    
    public <S extends T> List<S> findAll(Example<S> example, Sort sort);
    
    public Page<T> findAll(Pageable pageable);

    
    public <S extends T> S save(S entity);

    
    public Optional<T> findById(String id);

    
    public boolean existsById(String id);

    
    public long count();

    
    public void deleteById(String id);
    
    public void delete(T entity);

    
    public void deleteAll(Iterable<? extends T> entities);

    
    public void deleteAll();

    
    public <S extends T> Optional<S> findOne(Example<S> example);

    
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    
    public <S extends T> long count(Example<S> example);

    
    public <S extends T> boolean exists(Example<S> example);

    
    public List<T> findByName(String name);

}