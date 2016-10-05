package com.sudhu.elasticapp.generic.dao.domain;

import java.sql.Driver;

/**
 * Created by sudha on 01-Oct-16.
 */
public class DBConnectionVO {

    private static DBConnectionVO ourInstance = new DBConnectionVO();

    public static DBConnectionVO getInstance() {
        return ourInstance;
    }

    private DBConnectionVO() {
    }

    private String connectionString;

    private String userName;

    private String password;

    private Class<Driver> dbType;


    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Class<Driver> getDbType() {
        return dbType;
    }

    public void setDbType(Class<Driver> dbType) {
        this.dbType = dbType;
    }
}
