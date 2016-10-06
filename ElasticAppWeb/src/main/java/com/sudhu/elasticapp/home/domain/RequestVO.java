package com.sudhu.elasticapp.home.domain;

/**
 * Created by sudha on 06-Oct-16.
 */
public class RequestVO {
    private int requestID;

    private String queryName;

    private int reqAppId;

    private int reqQueryTypeId;

    private int reqFreqId;

    private int reqStatusId;

    private String dbServerName;

    private String dbPortNumber;

    private String dbName;

    private String dbPassword;

    private String connectionURL;


    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public int getReqAppId() {
        return reqAppId;
    }

    public void setReqAppId(int reqAppId) {
        this.reqAppId = reqAppId;
    }

    public int getReqQueryTypeId() {
        return reqQueryTypeId;
    }

    public void setReqQueryTypeId(int reqQueryTypeId) {
        this.reqQueryTypeId = reqQueryTypeId;
    }

    public int getReqFreqId() {
        return reqFreqId;
    }

    public void setReqFreqId(int reqFreqId) {
        this.reqFreqId = reqFreqId;
    }

    public int getReqStatusId() {
        return reqStatusId;
    }

    public void setReqStatusId(int reqStatusId) {
        this.reqStatusId = reqStatusId;
    }

    public String getDbServerName() {
        return dbServerName;
    }

    public void setDbServerName(String dbServerName) {
        this.dbServerName = dbServerName;
    }

    public String getDbPortNumber() {
        return dbPortNumber;
    }

    public void setDbPortNumber(String dbPortNumber) {
        this.dbPortNumber = dbPortNumber;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }
}
