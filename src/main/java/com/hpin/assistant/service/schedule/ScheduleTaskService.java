package com.hpin.assistant.service.schedule;

import com.hpin.assistant.job.AttachementMailJob;
import com.hpin.assistant.job.AttachementMailJobParameter;
import com.hpin.assistant.job.JobConstant;
import com.hpin.assistant.management.StatusManagement;
import com.hpin.assistant.utils.JsonUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huaiku
 * @date 2019年1月21日
 * @desc 任务相关的处理 CRUD ...
 */

@Service
public class ScheduleTaskService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTaskService.class);

    @Autowired
    Scheduler quartzScheduler;

    public void addTask(AttachementMailJobParameter jobDescribe, StatusManagement status) throws SchedulerException {
        this.buildTrigger(jobDescribe);
        status.done();
        logger.info("add job success ... {}", JsonUtils.toJson(jobDescribe));
    }

    /**
     * i. 删除任务
     *
     * @return
     */
    public String deleteTask() {
        return null;
    }

    public void restart() {

    }

    /**
     * i. 暂停任务调度
     *
     * @return
     */
    public void pauseTask() {

    }

    /**
     * i. 创建调度触发器
     *
     * @param jobDescribe
     * @return
     */
    private void buildTrigger(AttachementMailJobParameter jobDescribe) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(AttachementMailJob.class).withIdentity(jobDescribe.getJobName(), jobDescribe.getJobGroup()).build();
        jobDetail.getJobDataMap().put(JobConstant.JOB_DESCRIBE.name(), jobDescribe);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobDescribe.getCronExpression());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobDescribe.getJobName(), jobDescribe.getJobGroup()).withSchedule(cronScheduleBuilder).build();
        this.quartzScheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
