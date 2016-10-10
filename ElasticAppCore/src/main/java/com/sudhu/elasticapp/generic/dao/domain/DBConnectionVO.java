package com.sudhu.elasticapp.generic.dao.domain;

import java.sql.Driver;

/**
 * Created by sudha on 01-Oct-16.
 */
public class DBConnectionVO {

    public DBConnectionVO() {
    }

    private String connectionString;

    private String userName;

    private String password;

    private Class<? extends Driver> dbType;


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

    public Class<? extends Driver> getDbType() {
        return dbType;
    }

    public void setDbType(Class<? extends Driver> dbType) {
        this.dbType = dbType;
    }
}
