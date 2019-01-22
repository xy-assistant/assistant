package com.hpin.assistant.service.schedule;

import com.hpin.assistant.job.AttachementMailJobParameter;
import com.hpin.assistant.management.StatusManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author  huaiku
 * @date 2019年1月22日
 * @desc 包装
 */
@Service
public class ScheduleWrapperService {
    @Autowired
    MailAndSqlService mailAndSqlService;

    @Autowired
    ScheduleTaskService taskService;

    @Transactional(rollbackFor = Exception.class)
    public void addTask(AttachementMailJobParameter jobDescribe, StatusManagement status) {
        mailAndSqlService.addBindSqlAndEmail(jobDescribe);
        taskService.addTask(jobDescribe,status);
    }

    public AttachementMailJobParameter queryBindMailAndSql() {
        return null;
    }
}
