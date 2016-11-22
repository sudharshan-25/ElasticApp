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

	private String queryAnalyserType = "";

	private boolean analysed;
	
	
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

	/**
	 * @return the queryAnalyserType
	 */
	public String getQueryAnalyserType() {
		return queryAnalyserType;
	}

	/**
	 * @param queryAnalyserType
	 *            the queryAnalyserType to set
	 */
	public void setQueryAnalyserType(String queryAnalyserType) {
		this.queryAnalyserType = queryAnalyserType;
	}

	/**
	 * @return the analysed
	 */
	public boolean getAnalysed() {
		return analysed;
	}

	/**
	 * @param analysed the analysed to set
	 */
	public void setAnalysed(boolean analysed) {
		this.analysed = analysed;
	}

}
