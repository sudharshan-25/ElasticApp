package com.sudhu.elasticapp.common.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudha on 04-Oct-16.
 */
abstract public class AbstractCommonForm {

    private int userId;

    private String userName;

    private boolean errorForm;

    private List<String> errorMessages;

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



    public List<String> getErrorMessages() {
        if(this.errorMessages == null){
            this.errorMessages = new ArrayList<>();
        }
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isErrorForm() {
        if(null == this.errorMessages || this.errorMessages.isEmpty()){
           this.errorForm = false;
        }else{
            this.errorForm = true;
        }
        return errorForm;
    }

    public void setErrorForm(boolean errorForm) {
        this.errorForm = errorForm;
    }
}
