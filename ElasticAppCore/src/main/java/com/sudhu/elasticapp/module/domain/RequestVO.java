package com.sudhu.elasticapp.module.domain;

public class RequestVO {

	private int requestId;
	
	private String appToken;
	
	private ModuleVO moduleVO;
	
	private String queryName;
	
	private int projectId;
	
	private String queryType ;
	
	private String query;
	
	private int updateFreq;

	private int statusId;
	
	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the appToken
	 */
	public String getAppToken() {
		return appToken;
	}

	/**
	 * @param appToken the appToken to set
	 */
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	/**
	 * @return the moduleVO
	 */
	public ModuleVO getModuleVO() {
		return moduleVO;
	}

	/**
	 * @param moduleVO the moduleVO to set
	 */
	public void setModuleVO(ModuleVO moduleVO) {
		this.moduleVO = moduleVO;
	}

	/**
	 * @return the queryName
	 */
	public String getQueryName() {
		return queryName;
	}

	/**
	 * @param queryName the queryName to set
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the queryType
	 */
	public String getQueryType() {
		return queryType;
	}

	/**
	 * @param queryType the queryType to set
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the updateFreq
	 */
	public int getUpdateFreq() {
		return updateFreq;
	}

	/**
	 * @param updateFreq the updateFreq to set
	 */
	public void setUpdateFreq(int updateFreq) {
		this.updateFreq = updateFreq;
	}

	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	
	
}
