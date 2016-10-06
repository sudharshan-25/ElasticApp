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

    private int updateFreq;

    private List<DomainVO> updateFreqList;

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


    public List<DomainVO> getUpdateFreqList() {
        return updateFreqList;
    }

    public void setUpdateFreqList(List<DomainVO> updateFreqList) {
        this.updateFreqList = updateFreqList;
    }


    public int getUpdateFreq() {
        return updateFreq;
    }

    public void setUpdateFreq(int updateFreq) {
        this.updateFreq = updateFreq;
    }
}
