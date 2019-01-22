package com.hpin.assistant.management;

/**
 * @author  huaiku
 * @date 2019年1月21日
 * @desc 状态管理
 */
public class StatusManagement {
    private boolean done;
    private String errorMessage;
    private String info;
    public StatusManagement(){}
    public StatusManagement(boolean done,String info) {
        this.done = done;
        this.info = info;
    }
    public StatusManagement(boolean done,String errorMessage,String info) {
        this.done = done;
        this.errorMessage = errorMessage;
        this.info = info;
    }
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
