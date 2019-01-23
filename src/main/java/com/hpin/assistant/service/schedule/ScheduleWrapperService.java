package com.hpin.assistant.service.schedule;

import com.hpin.assistant.job.AttachementMailJobParameter;
import com.hpin.assistant.management.StatusManagement;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 包装
 */
@Service
public class ScheduleWrapperService {

    private static Logger logger = LoggerFactory.getLogger(ScheduleWrapperService.class);

    @Value("${assistant.file-export-path}")
    private String defaultPath;

    @Autowired
    MailAndSqlService mailAndSqlService;

    @Autowired
    ScheduleTaskService taskService;

    @Transactional(rollbackFor = Exception.class)
    public void addTask(AttachementMailJobParameter jobDescribe, StatusManagement status) throws SchedulerException {
        jobDescribe.getSqlInfo().setDefaultPath(defaultPath);
        mailAndSqlService.addBindSqlAndEmail(jobDescribe);
        jobDescribe.synId();
        taskService.addTask(jobDescribe, status);
    }

    public void queryBindMailAndSql(AttachementMailJobParameter jobDescribe) {
        this.mailAndSqlService.queryMainAndSqlInfo(jobDescribe);
    }
}
