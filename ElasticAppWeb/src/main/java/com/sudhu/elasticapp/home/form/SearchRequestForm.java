package com.sudhu.elasticapp.home.form;

public class SearchRequestForm {

	private String queryName;

	private String queryType;

	private String projectId;

	private String dbType;

	private String updateFreq;

	private String statusId;

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getUpdateFreq() {
		return updateFreq;
	}

	public void setUpdateFreq(String updateFreq) {
		this.updateFreq = updateFreq;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

}
