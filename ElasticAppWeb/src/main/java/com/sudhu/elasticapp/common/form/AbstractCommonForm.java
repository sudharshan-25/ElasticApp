package com.sudhu.elasticapp.common.form;

/**
 * Created by sudha on 04-Oct-16.
 */
abstract public class AbstractCommonForm {

	private int userId;

	private String userName;

	private boolean errorForm;

	private String errorMessages;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getErrorMessages() {

		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	public boolean isErrorForm() {
		if (null == this.errorMessages || this.errorMessages.isEmpty()) {
			this.errorForm = false;
		} else {
			this.errorForm = true;
		}
		return errorForm;
	}

	public void setErrorForm(boolean errorForm) {
		this.errorForm = errorForm;
	}
}
