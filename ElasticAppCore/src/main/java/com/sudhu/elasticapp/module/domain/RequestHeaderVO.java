package com.sudhu.elasticapp.module.domain;

public class RequestHeaderVO {

	private String queryId = "";
	
	private String queryName;
	
	private String appToken;
	
	private String connectionURL;
	
	private String queryApplication;
	
	private String queryType;
	
	private String queryFrequency;
	
	private String queryStatus;

	/**
	 * @return the queryId
	 */
	public String getQueryId() {
		return queryId;
	}

	/**
	 * @param queryId the queryId to set
	 */
	public void setQueryId(String queryId) {
		this.queryId = queryId;
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
	 * @return the connectionURL
	 */
	public String getConnectionURL() {
		return connectionURL;
	}

	/**
	 * @param connectionURL the connectionURL to set
	 */
	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	/**
	 * @return the queryApplication
	 */
	public String getQueryApplication() {
		return queryApplication;
	}

	/**
	 * @param queryApplication the queryApplication to set
	 */
	public void setQueryApplication(String queryApplication) {
		this.queryApplication = queryApplication;
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
	 * @return the queryFrequency
	 */
	public String getQueryFrequency() {
		return queryFrequency;
	}

	/**
	 * @param queryFrequency the queryFrequency to set
	 */
	public void setQueryFrequency(String queryFrequency) {
		this.queryFrequency = queryFrequency;
	}

	/**
	 * @return the queryStatus
	 */
	public String getQueryStatus() {
		return queryStatus;
	}

	/**
	 * @param queryStatus the queryStatus to set
	 */
	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
	
	
}
