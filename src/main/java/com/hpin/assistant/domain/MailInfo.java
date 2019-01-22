package com.hpin.assistant.domain;

import com.hpin.assistant.utils.JsonUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 邮件信息对象
 */
public class MailInfo implements Serializable {

    private static final long serialVersionUID = 100L;

    private long id;

    private String subject;

    private String from;

    private String message;

    private String nickName;

    private Date createDate;

    private Date modifyDate;

    private String copyList;

    private String sendList;

    private transient String[] cc;

    private transient String[] to;

    private transient Map<String,String> attachments;

    public String getCopyList() {
        return copyList;
    }

    public void setCopyList(String copyList) {
        this.copyList = copyList;
    }

    public String getSendList() {
        return sendList;
    }

    public void setSendList(String sendList) {
        this.sendList = sendList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getCc() {
        return this.copyList == null? null: this.copyList.split(";");
    }

    public String[] getTo() {
        return this.sendList == null ? null:this.sendList.split(";");
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
