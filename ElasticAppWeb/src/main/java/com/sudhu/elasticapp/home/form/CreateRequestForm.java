package com.sudhu.elasticapp.home.form;

import com.sudhu.elasticapp.common.form.AbstractCommonForm;
import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.ModuleVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudha on 04-Oct-16.
 */
public class CreateRequestForm extends AbstractCommonForm {

    private String queryName;

    private ModuleVO moduleVO;

    private int projectId;

    private List<DomainVO> availableProjects;

    private String queryType ;

    private List<DomainVO> queryTypes;

    private String query;

    private String tableName;

    private String appToken;

    private List<DomainVO> dbTypes;

    private int databaseVendorId;

    private String dataBaseName;

    private String dbServerName;

    private int dbPortNumber;

    private String dbConnectionURL;

    private String dbUserName = "";

    private String dbPassword = "";

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public ModuleVO getModuleVO() {
        return moduleVO;
    }

    public void setModuleVO(ModuleVO moduleVO) {
        this.moduleVO = moduleVO;
    }

    public List<DomainVO> getAvailableProjects() {

        if(null==availableProjects){
            this.availableProjects = new ArrayList<>();
        }

        return availableProjects;
    }

    public void setAvailableProjects(List<DomainVO> availableProjects) {
        this.availableProjects = availableProjects;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public List<DomainVO> getQueryTypes() {
        if(null==queryTypes){
            this.queryTypes = new ArrayList<>();
        }
        return queryTypes;
    }

    public void setQueryTypes(List<DomainVO> queryTypes) {
        this.queryTypes = queryTypes;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<DomainVO> getDbTypes() {
        if(null==this.dbTypes){
            this.dbTypes = new ArrayList<>();
        }
        return dbTypes;
    }

    public void setDbTypes(List<DomainVO> dbTypes) {
        this.dbTypes = dbTypes;
    }

    public int getDatabaseVendorId() {
        return databaseVendorId;
    }

    public void setDatabaseVendorId(int databaseVendorId) {
        this.databaseVendorId = databaseVendorId;
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

    public int getDbPortNumber() {
        return dbPortNumber;
    }

    public void setDbPortNumber(int dbPortNumber) {
        this.dbPortNumber = dbPortNumber;
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
