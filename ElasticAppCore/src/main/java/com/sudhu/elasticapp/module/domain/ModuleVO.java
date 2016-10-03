package com.sudhu.elasticapp.module.domain;

/**
 * Created by sudha on 01-Oct-16.
 */
public class ModuleVO {

    private String moduleName;

    private String dataBaseName;

    private String dbServerName;

    private String dbConnectionURL;

    private String dbUserName;

    private String dbPassword;


    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDbServerName() {
        return dbServerName;
    }

    public void setDbServerName(String dbServerName) {
        this.dbServerName = dbServerName;
    }

    public String getDbConnectionURL() {
        return dbConnectionURL;
    }

    public void setDbConnectionURL(String dbConnectionURL) {
        this.dbConnectionURL = dbConnectionURL;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
