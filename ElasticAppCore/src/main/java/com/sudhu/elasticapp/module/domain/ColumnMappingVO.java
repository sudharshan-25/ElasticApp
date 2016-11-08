/**
 * 
 */
package com.sudhu.elasticapp.module.domain;

/**
 * @author sudha
 *
 */
public class ColumnMappingVO {

	private String queryId = "";

	private String columnName;

	private String queryDataType;

	private boolean indexed;

	/**
	 * @return the indexed
	 */
	public boolean getIndexed() {
		return indexed;
	}

	/**
	 * @param indexed
	 *            the indexed to set
	 */
	public void setIndexed(boolean indexed) {
		this.indexed = indexed;
	}

	/**
	 * @return the queryId
	 */
	public String getQueryId() {
		return queryId;
	}

	/**
	 * @param queryId
	 *            the queryId to set
	 */
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the queryDataType
	 */
	public String getQueryDataType() {
		return queryDataType;
	}

	/**
	 * @param queryDataType
	 *            the queryDataType to set
	 */
	public void setQueryDataType(String queryDataType) {
		this.queryDataType = queryDataType;
	}

}
