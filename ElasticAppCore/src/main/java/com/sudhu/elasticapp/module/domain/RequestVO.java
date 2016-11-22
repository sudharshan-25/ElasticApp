package com.sudhu.elasticapp.module.domain;

import java.util.ArrayList;
import java.util.List;

public class RequestVO {

	private String requestId = "";

	private String appToken;

	private ModuleVO moduleVO;

	private List<ColumnMappingVO> columnMapping;

	private String queryName;

	private String projectId = "";

	private String queryType="";

	private String query;
	
	private String updateFreq="";

	private String statusId="";

	private String idColumn = "";
		
	private String emailNotification;
	
	private boolean reIndexData;
	
	/**
	 * @return the reIndexData
	 */
	public boolean isReIndexData() {
		return reIndexData;
	}

	/**
	 * @param reIndexData the reIndexData to set
	 */
	public void setReIndexData(boolean reIndexData) {
		this.reIndexData = reIndexData;
	}

	/**
	 * @return the columnMapping
	 */
	public List<ColumnMappingVO> getColumnMapping() {
		if (this.columnMapping == null) {
			this.columnMapping = new ArrayList<>();
		}
		return columnMapping;
	}

	/**
	 * @param columnMapping
	 *            the columnMapping to set
	 */
	public void setColumnMapping(List<ColumnMappingVO> columnMapping) {
		this.columnMapping = columnMapping;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the appToken
	 */
	public String getAppToken() {
		return appToken;
	}

	/**
	 * @param appToken
	 *            the appToken to set
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
	 * @param moduleVO
	 *            the moduleVO to set
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
	 * @param queryName
	 *            the queryName to set
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the queryType
	 */
	public String getQueryType() {
		return queryType;
	}

	/**
	 * @param queryType
	 *            the queryType to set
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
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the updateFreq
	 */
	public String getUpdateFreq() {
		return updateFreq;
	}

	/**
	 * @param updateFreq
	 *            the updateFreq to set
	 */
	public void setUpdateFreq(String updateFreq) {
		this.updateFreq = updateFreq;
	}

	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the idColumn
	 */
	public String getIdColumn() {
		return idColumn;
	}

	/**
	 * @param idColumn the idColumn to set
	 */
	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	/**
	 * @return the emailNotification
	 */
	public String getEmailNotification() {
		return emailNotification;
	}

	/**
	 * @param emailNotification the emailNotification to set
	 */
	public void setEmailNotification(String emailNotification) {
		this.emailNotification = emailNotification;
	}

}
