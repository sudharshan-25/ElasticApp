package com.sudhu.elasticapp.module.domain;

/**
 * Created by 422626 on 10/5/2016.
 */
public class DomainVO {

	public DomainVO() {

	}

	public DomainVO(String id, String key, String value) {
		this.id = id;
		this.key = key;
		this.value = value;
	}

	private String id = "";

	private String key;

	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
