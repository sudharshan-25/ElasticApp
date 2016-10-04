package com.sudhu.elasticapp.home.form;

import com.sudhu.elasticapp.common.constants.CommonConstants;
import com.sudhu.elasticapp.common.form.AbstractCommonForm;

import java.util.List;

/**
 * Created by sudha on 04-Oct-16.
 */
public class CreateRequestForm extends AbstractCommonForm {

    private String applicationName;

    private String queryType = CommonConstants.QUERY_TYPE_SIMPLE;

    private String query;

    private String tableName;

    private String appToken;

    private List<String>  queryTypes;


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
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

    public List<String> getQueryTypes() {
        return queryTypes;
    }

    public void setQueryTypes(List<String> queryTypes) {
        this.queryTypes = queryTypes;
    }
}
