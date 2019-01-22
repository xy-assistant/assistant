package com.hpin.assistant.job;

import com.hpin.assistant.domain.MailInfo;
import com.hpin.assistant.domain.TaskBindSqlInfo;
import com.hpin.assistant.utils.JsonUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author huaiku
 * @date 2019年1月21日
 */
public class AttachementMailJobParameter implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String jobName;
    private String jobGroup;
    private String jobTrigger;
    private String cronExpression;
    private Boolean isSync = false;
    private String description;
    private Set<String> cities;
    private long mailInfoId;
    private long sqlInfoId;
    private Date updatedTime = new Date();
    private transient MailInfo mailInfo;
    private transient TaskBindSqlInfo sqlInfo;

    public MailInfo getMailInfo() {
        return mailInfo;
    }

    public void setMailInfo(MailInfo mailInfo) {
        this.mailInfo = mailInfo;
    }

    public TaskBindSqlInfo getSqlInfo() {
        return sqlInfo;
    }

    public void setSqlInfo(TaskBindSqlInfo sqlInfo) {
        this.sqlInfo = sqlInfo;
    }

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

    public long getMailInfoId() {
        return mailInfoId;
    }

    public void setMailInfoId(long mailInfoId) {
        this.mailInfoId = mailInfoId;
    }

    public long getSqlInfoId() {
        return sqlInfoId;
    }

    public void setSqlInfoId(long sqlInfoId) {
        this.sqlInfoId = sqlInfoId;
    }

    public Set<String> getCities() {
        return cities;
    }

    public void setCities(Set<String> cities) {
        this.cities = cities;
    }

    public void setCtiy(String cityCode) {
        if (Objects.nonNull(this.cities)) {
            this.cities.add(cityCode);
        } else {
            this.cities = new HashSet<>();
            this.cities.add(cityCode);
        }
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
