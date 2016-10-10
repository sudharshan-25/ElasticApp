package com.sudhu.elasticapp.home.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sudha on 07-Oct-16.
 */

public class AbstractResponseVO {

    @JsonProperty("error")
    private String errorMessage;

    @JsonProperty("success")
    private String message;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
