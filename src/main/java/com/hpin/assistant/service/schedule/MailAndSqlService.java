package com.hpin.assistant.service.schedule;

import com.hpin.assistant.dao.mapper.ScheduledMailInfoMapper;
import com.hpin.assistant.dao.mapper.ScheduledSqlInfoMapper;
import com.hpin.assistant.domain.MailInfo;
import com.hpin.assistant.domain.TaskBindSqlInfo;
import com.hpin.assistant.job.AttachementMailJobParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 邮件和sql 信息
 */
@Service
public class MailAndSqlService {
    private static Logger logger = LoggerFactory.getLogger(MailAndSqlService.class);

    @Autowired
    ScheduledMailInfoMapper scheduledMailInfoMapper;
    @Autowired
    ScheduledSqlInfoMapper scheduledSqlInfoMapper;

    /**
     *
     * i. 保存邮件和sql信信息
     * @param jobDescribe
     *
     *
     *
     *
     * @return
     *
     */
    public void addBindSqlAndEmail(AttachementMailJobParameter jobDescribe) {
        if (Objects.nonNull(jobDescribe.getMailInfo())) {
            scheduledMailInfoMapper.insertMainInfo(jobDescribe.getMailInfo());
        }

        if (Objects.nonNull(jobDescribe.getSqlInfo())) {
            scheduledSqlInfoMapper.insertSqlInfo(jobDescribe.getSqlInfo());
        }
    }

    public void queryMainAndSqlInfo(AttachementMailJobParameter jobDescribe) {
        MailInfo mailInfo = this.scheduledMailInfoMapper.queryById(jobDescribe.getMailInfoId());
        jobDescribe.setMailInfo(mailInfo);
        TaskBindSqlInfo sqlInfo = this.scheduledSqlInfoMapper.queryById(jobDescribe.getSqlInfoId());
        jobDescribe.setSqlInfo(sqlInfo);
    }
}
