package org.example.dao;

import java.util.List;

public interface Dao<E> {

    public E readFirst() throws DaoException;

    /**
     * Ova metoda treba da pročita trajno snimljene podatke tipa E i da nam
     * vrati listu učitanih objekata {@link List}
     * <p>
     * @return list of entities
     * @throws DaoException
     */
    public List<E> readAll() throws DaoException;

    public void write(E entity) throws DaoException;

    /**
     * Ove metoda treba da snimi trajno podatke koje je dobila kao parametar.
     * <p>
     * @param entites
     * @throws DaoException
     */
    public void writeAll(List<E> entites) throws DaoException;
}
