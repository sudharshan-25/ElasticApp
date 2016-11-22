package com.sudhu.elasticapp.home.form;

import java.util.List;

import com.sudhu.elasticapp.module.domain.DomainVO;

public class RequestForm {

	private List<DomainVO> dataTypeList;

	private List<DomainVO> availableProjects;

	private List<DomainVO> queryTypes;

	private List<DomainVO> dbTypes;

	private List<DomainVO> updateFreqList;
	
	private List<DomainVO> statusList;

	private List<DomainVO> analyserList;
	
	public List<DomainVO> getDataTypeList() {
		return dataTypeList;
	}

	public void setDataTypeList(List<DomainVO> dataTypeList) {
		this.dataTypeList = dataTypeList;
	}

	public List<DomainVO> getAvailableProjects() {
		return availableProjects;
	}

	public void setAvailableProjects(List<DomainVO> availableProjects) {
		this.availableProjects = availableProjects;
	}

	public List<DomainVO> getQueryTypes() {
		return queryTypes;
	}

	public void setQueryTypes(List<DomainVO> queryTypes) {
		this.queryTypes = queryTypes;
	}

	public List<DomainVO> getDbTypes() {
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

	public List<DomainVO> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<DomainVO> statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the analyserList
	 */
	public List<DomainVO> getAnalyserList() {
		return analyserList;
	}

	/**
	 * @param analyserList the analyserList to set
	 */
	public void setAnalyserList(List<DomainVO> analyserList) {
		this.analyserList = analyserList;
	}

}
