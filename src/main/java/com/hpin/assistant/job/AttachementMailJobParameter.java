package com.hpin.assistant.job;
import com.hpin.assistant.utils.JsonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huaiku
 * @date 2019年1月21日
 */
public class AttachementMailJobParameter implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String jobName;
    private String jobGroup;
    private String jobTrigger;
    private String status;
    private String cronExpression;
    private Boolean isSync = false;
    private String description;
    private Date updatedTime = new Date();

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
