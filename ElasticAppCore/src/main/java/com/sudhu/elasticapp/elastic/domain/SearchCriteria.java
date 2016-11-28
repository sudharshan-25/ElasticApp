package com.sudhu.elasticapp.elastic.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.sudhu.elasticapp.common.helper.GeneralUtils;

public class SearchCriteria {

	@NotNull
	private String appToken;

	private int size = 10;

	private long pageNumber = 1;

	private String resultField;

	private List<String> fields = new ArrayList<>();

	private List<SearchField> allMatch = new ArrayList<>();

	private List<SearchField> noMatch = new ArrayList<>();

	private List<SearchField> fewMatch = new ArrayList<>();

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
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the pageNumber
	 */
	public long getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the allMatch
	 */
	public List<SearchField> getAllMatch() {
		return allMatch;
	}

	/**
	 * @param allMatch
	 *            the allMatch to set
	 */
	public void setAllMatch(List<SearchField> allMatch) {
		this.allMatch = allMatch;
	}

	/**
	 * @return the noMatch
	 */
	public List<SearchField> getNoMatch() {
		return noMatch;
	}

	/**
	 * @param noMatch
	 *            the noMatch to set
	 */
	public void setNoMatch(List<SearchField> noMatch) {
		this.noMatch = noMatch;
	}

	/**
	 * @return the fewMatch
	 */
	public List<SearchField> getFewMatch() {
		return fewMatch;
	}

	/**
	 * @param fewMatch
	 *            the fewMatch to set
	 */
	public void setFewMatch(List<SearchField> fewMatch) {
		this.fewMatch = fewMatch;
	}

	/**
	 * @return the resultField
	 */
	public String getResultField() {
		return resultField;
	}

	/**
	 * @param resultField
	 *            the resultField to set
	 */
	public void setResultField(String resultField) {
		this.resultField = resultField;
	}

	/**
	 * @return the fields
	 */
	public List<String> getFields() {
		if (GeneralUtils.isValidString(resultField)) {
			fields = Arrays.asList(resultField.split(","));
		}
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<String> fields) {
		this.fields = fields;
	}

}
