/**
 * 
 */
package com.sudhu.elasticapp.home.form;

import java.util.List;

import com.sudhu.elasticapp.common.form.AbstractCommonForm;
import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.ModuleVO;

/**
 * @author sudha
 *
 */
public class UpdateRequestForm extends AbstractCommonForm {

	private int queryId;
	
	/**
	 * @return the queryId
	 */
	public int getQueryId() {
		return queryId;
	}

	/**
	 * @param queryId the queryId to set
	 */
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	private String queryName;

	private ModuleVO moduleVO;

	private int projectId;

	private String queryType;

	private String query;

	private String tableName;

	private String appToken;

	private int updateFreq;

	private int statusId;
	
	private List<DomainVO> availableProjects;

	private List<DomainVO> queryTypes;

	private List<DomainVO> dbTypes;

	private List<DomainVO> updateFreqList;
	
	private List<DomainVO> statusList;

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
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	/**
	 * @return the availableProjects
	 */
	public List<DomainVO> getAvailableProjects() {
		return availableProjects;
	}

	/**
	 * @param availableProjects the availableProjects to set
	 */
	public void setAvailableProjects(List<DomainVO> availableProjects) {
		this.availableProjects = availableProjects;
	}

	/**
	 * @return the queryTypes
	 */
	public List<DomainVO> getQueryTypes() {
		return queryTypes;
	}

	/**
	 * @param queryTypes the queryTypes to set
	 */
	public void setQueryTypes(List<DomainVO> queryTypes) {
		this.queryTypes = queryTypes;
	}

	/**
	 * @return the dbTypes
	 */
	public List<DomainVO> getDbTypes() {
		return dbTypes;
	}

	/**
	 * @param dbTypes the dbTypes to set
	 */
	public void setDbTypes(List<DomainVO> dbTypes) {
		this.dbTypes = dbTypes;
	}

	/**
	 * @return the updateFreqList
	 */
	public List<DomainVO> getUpdateFreqList() {
		return updateFreqList;
	}

	/**
	 * @param updateFreqList the updateFreqList to set
	 */
	public void setUpdateFreqList(List<DomainVO> updateFreqList) {
		this.updateFreqList = updateFreqList;
	}

	/**
	 * @return the statusList
	 */
	public List<DomainVO> getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(List<DomainVO> statusList) {
		this.statusList = statusList;
	}
	
}
