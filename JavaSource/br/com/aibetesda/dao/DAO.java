package br.com.aibetesda.dao;

import java.util.List;

public interface DAO<T> {
    public T create(T newInstance) throws Exception;
    public void delete(Long id) throws Exception;
    public T read(Long id);
    public List<T> readAll();
}
