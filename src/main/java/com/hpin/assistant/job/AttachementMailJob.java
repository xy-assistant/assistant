package com.hpin.assistant.job;

import com.hpin.assistant.bootstrap.ApplicationContextHolder;
import com.hpin.assistant.service.DynamicSourceService;
import com.hpin.assistant.service.FileExportService;
import com.hpin.assistant.utils.JsonUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author huaiku
 * @date 2019年1月21日
 * @desc 带附件的邮件
 */
public class AttachementMailJob implements Job {

    private Logger logger = LoggerFactory.getLogger("AttachementMailJob");
    @Override
    public void execute(JobExecutionContext context) {
        AttachementMailJobParameter jobParam = (AttachementMailJobParameter) context.getJobDetail().getJobDataMap()
                .get(JobConstant.JOB_DESCRIBE.name());

        if (Objects.nonNull(jobParam)) {
            // sql executer
            DynamicSourceService service = ApplicationContextHolder.getBean("fileExportService", FileExportService.class);
            // sql and mail query

            JobKey jobKey = context.getJobDetail().getKey();
            logger.info("Job名称：{}，JobKey信息：{}",jobParam.getJobName(), JsonUtils.toJson(jobKey));
        }
    }
}
