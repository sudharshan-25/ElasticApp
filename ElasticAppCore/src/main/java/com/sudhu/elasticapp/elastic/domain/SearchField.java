package com.sudhu.elasticapp.elastic.domain;

import javax.validation.constraints.NotNull;

public class SearchField {

	@NotNull
	private String name;

	@NotNull
	private String value;

	private String from;

	private String to;

	private String operator;

	private boolean wildcard = true;

	public SearchField() {

	}

	public SearchField(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return  operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the wildcard
	 */
	public boolean getWildcard() {
		return wildcard;
	}

	/**
	 * @param wildcard
	 *            the wildcard to set
	 */
	public void setWildcard(boolean wildcard) {
		this.wildcard = wildcard;
	}

}
