package com.sudhu.elasticapp.elastic.domain;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {

	private long size = 10;

	private long pageNumber = 1;

	private List<SearchField> allMatch = new ArrayList<>();

	private List<SearchField> noMatch = new ArrayList<>();

	private List<SearchField> fewMatch = new ArrayList<>();

	private List<String> fields = new ArrayList<>();

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(long size) {
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
	 * @return the fields
	 */
	public List<String> getFields() {
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
