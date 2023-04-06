package org.example.dao;

public class DaoException extends Exception{

    public DaoException(String message){
        super("DAO: " + message);
    }
}
