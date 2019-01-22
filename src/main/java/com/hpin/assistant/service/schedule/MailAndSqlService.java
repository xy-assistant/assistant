package com.hpin.assistant.service.schedule;

import com.hpin.assistant.dao.mapper.ScheduledMailInfoMapper;
import com.hpin.assistant.dao.mapper.ScheduledSqlInfoMapper;
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
     * @return
     *
     */
    public void addBindSqlAndEmail(AttachementMailJobParameter jobDescribe) {
        Integer rowEffected;
        if (Objects.nonNull(jobDescribe.getMailInfo())) {
            rowEffected = scheduledMailInfoMapper.insertMainInfo(jobDescribe.getMailInfo());
            logger.info("insertMainInfo 受影响行数：{}",rowEffected);
        }

        if (Objects.nonNull(jobDescribe.getSqlInfo())) {
            rowEffected = scheduledSqlInfoMapper.insertSqlInfo(jobDescribe.getSqlInfo());
            logger.info("insertSqlInfo 受影响行数：{}",rowEffected);
        }
    }
}
