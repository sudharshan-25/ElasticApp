package com.sudhu.elasticapp.generic.dao.exception;

/**
 * Created by sudha on 01-Oct-16.
 */
public class DBConnectionException extends Exception {

    public DBConnectionException(String message){
        super(message);
    }

    public DBConnectionException(Exception ex){
        super(ex);
    }

    public DBConnectionException (Exception ex, String message){
        super(message, ex);
    }


}
