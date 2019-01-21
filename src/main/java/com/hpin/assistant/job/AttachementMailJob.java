package com.hpin.assistant.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author huaiku
 * @date 2019年1月21日
 * @desc 带附件的邮件
 */
public class AttachementMailJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        AttachementMailJobParameter jobParam = (AttachementMailJobParameter) context.getJobDetail().getJobDataMap()
                .get(JobConstant.JOB_DESCRIBE.name());
        if (jobParam != null) {
            System.out.println(jobParam.getDescription());
        } else {
            System.out.println("Hey, can‘t find job parameter ...:)");
        }
    }
}
