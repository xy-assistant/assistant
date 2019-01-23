package com.hpin.assistant.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 任务绑定的sql信息ID
 */

public class TaskBindSqlInfo implements Serializable {

    private static final long serialVersionUID = 101L;
    private Integer id;
    private String sqlText;
    private Date createDate;
    private Date modifyDate;
    private String defaultPath;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExcelFileName() {
        return this.fileName+".xlsx";
    }
    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
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
}
