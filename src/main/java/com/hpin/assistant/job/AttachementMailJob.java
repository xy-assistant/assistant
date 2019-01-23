package com.hpin.assistant.job;

import com.hpin.assistant.bootstrap.ApplicationContextHolder;
import com.hpin.assistant.service.DynamicSourceService;
import com.hpin.assistant.service.FileExportService;
import com.hpin.assistant.service.mail.EmailService;
import com.hpin.assistant.service.mail.MailServiceImpl;
import com.hpin.assistant.service.schedule.ScheduleWrapperService;
import com.hpin.assistant.utils.ExportUtils;
import com.hpin.assistant.utils.JsonUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.*;

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

            String sqlText = this.getSqlText(jobParam);
            if (Objects.nonNull(sqlText)) {

                List<String> titles = new ArrayList<>();
                List<Object[]> objects =  this.getExportData(jobParam,titles);

                if (Objects.nonNull(objects) && Objects.nonNull(titles)) {

                    String fullPath;
                    String parentPath = this.getParentPath(jobParam.getSqlInfo().getDefaultPath());
                    File newFile = new File(parentPath, jobParam.getSqlInfo().getExcelFileName());

                    if (newFile.exists()) {
                        // 临时保存一个带时间戳的文件名
                        jobParam.getSqlInfo().setFileName(jobParam.getSqlInfo().getFileName() + System.currentTimeMillis());
                        newFile = new File(parentPath, jobParam.getSqlInfo().getExcelFileName());
                    }

                    File parent = newFile.getParentFile();
                    // 当文件夹不存在时创建文件夹
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }

                    fullPath = parentPath + File.separator + jobParam.getSqlInfo().getExcelFileName();

                    OutputStream out = null;
                    try {
                        newFile.createNewFile();
                        out = new FileOutputStream(newFile);
                        ExportUtils.exportExcel(titles,objects,out);
                        logger.info("生成文件信息：{}",fullPath);
                        // 通知发邮件
                        if (Objects.nonNull(jobParam.getMailInfo()) && Objects.nonNull(jobParam.getMailInfo().getTo())) {
                            Map<String,String> attachmentMap = new HashMap<>();
                            attachmentMap.put(jobParam.getSqlInfo().getExcelFileName(),fullPath);
                            jobParam.getMailInfo().setAttachments(attachmentMap);
                            this.mailNotification(jobParam);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (out!=null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * i. 通知发邮件
     * @param jobParam
     */
    private void mailNotification(AttachementMailJobParameter jobParam) {
        logger.info("发送邮件通知...");
        EmailService mailService = ApplicationContextHolder.getBean("mailServiceImpl", MailServiceImpl.class);
        mailService.notification(jobParam.getMailInfo());
        logger.info("发送邮件通知完成...");
    }

    /**
     * i. 获取sql 并且同时设置mail和sql相关信息
     * @param jobParam
     * @return
     */
    private String getSqlText(AttachementMailJobParameter jobParam) {
        logger.info("获取任务关联的邮件和sql定义信息...");
        // 获取数据库保存的待执行sql和待发送mail信息
        ScheduleWrapperService scheduleWrapperService = ApplicationContextHolder.getBean("scheduleWrapperService", ScheduleWrapperService.class);
        scheduleWrapperService.queryBindMailAndSql(jobParam);
        return jobParam.getSqlInfo().getSqlText();
    }

    /**
     * i. 获取导表数据和标题
     * @param jobParam
     * @param titles
     * @return
     */
    private List<Object[]> getExportData(AttachementMailJobParameter jobParam,List<String> titles) {
        logger.info("获取导表数据...");
        DynamicSourceService service = ApplicationContextHolder.getBean("fileExportService", FileExportService.class);
        return service.query(jobParam.getCityCode(),titles,jobParam.getSqlInfo().getSqlText());
    }

    /**
     * i. your path/year/month/day/
     *
     * @return your path/year/month/day/
     */
    private String getParentPath(String defaultPath) {
        LocalDate now = LocalDate.now();
        return new StringBuilder(defaultPath)
                .append(File.separator)
                .append(now.getYear())
                .append(File.separator)
                .append(now.getMonthValue())
                .append(File.separator)
                .append(now.getDayOfMonth())
                .toString();
    }
}
