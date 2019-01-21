package com.hpin.assistant.management;

/**
 * @author  huaiku
 * @date 2019年1月21日
 * @desc 状态管理
 */
public class StatusManagement {
    private boolean done;
    private String errorMessage;

    public void done() {
        this.done = true;
    }
    public boolean isDone() {
        return this.done;
    }
    public void failed() {
        this.done = false;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
