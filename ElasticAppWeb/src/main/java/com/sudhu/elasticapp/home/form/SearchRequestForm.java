package com.sudhu.elasticapp.home.form;

import java.util.List;

import com.sudhu.elasticapp.common.form.AbstractCommonForm;
import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.RequestHeaderVO;

public class SearchRequestForm extends AbstractCommonForm {

	private String queryName;

	private int queryType;

	private int projectId;

	private int dbType;

	private int updateFreq;

	private List<DomainVO> queryTypes;

	private List<DomainVO> availableProjects;

	private List<DomainVO> dbTypes;

	private List<DomainVO> updateFreqList;

	private List<RequestHeaderVO> searchResults;

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
	 * @return the queryType
	 */
	public int getQueryType() {
		return queryType;
	}

	/**
	 * @param queryType
	 *            the queryType to set
	 */
	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the dbType
	 */
	public int getDbType() {
		return dbType;
	}

	/**
	 * @param dbType
	 *            the dbType to set
	 */
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}

	/**
	 * @return the updateFreq
	 */
	public int getUpdateFreq() {
		return updateFreq;
	}

	/**
	 * @param updateFreq
	 *            the updateFreq to set
	 */
	public void setUpdateFreq(int updateFreq) {
		this.updateFreq = updateFreq;
	}

	/**
	 * @return the queryTypes
	 */
	public List<DomainVO> getQueryTypes() {
		return queryTypes;
	}

	/**
	 * @param queryTypes
	 *            the queryTypes to set
	 */
	public void setQueryTypes(List<DomainVO> queryTypes) {
		this.queryTypes = queryTypes;
	}

	/**
	 * @return the availableProjects
	 */
	public List<DomainVO> getAvailableProjects() {
		return availableProjects;
	}

	/**
	 * @param availableProjects
	 *            the availableProjects to set
	 */
	public void setAvailableProjects(List<DomainVO> availableProjects) {
		this.availableProjects = availableProjects;
	}

	/**
	 * @return the dbTypes
	 */
	public List<DomainVO> getDbTypes() {
		return dbTypes;
	}

	/**
	 * @param dbTypes
	 *            the dbTypes to set
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
	 * @param updateFreqList
	 *            the updateFreqList to set
	 */
	public void setUpdateFreqList(List<DomainVO> updateFreqList) {
		this.updateFreqList = updateFreqList;
	}

	/**
	 * @return the searchResults
	 */
	public List<RequestHeaderVO> getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            the searchResults to set
	 */
	public void setSearchResults(List<RequestHeaderVO> searchResults) {
		this.searchResults = searchResults;
	}

}
