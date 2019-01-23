package com.hpin;

import com.hpin.assistant.bootstrap.DataSourceKeyStore;
import com.hpin.assistant.bootstrap.DynamicDataSourceRegister;
import com.hpin.assistant.domain.MailInfo;
import com.hpin.assistant.domain.TaskBindSqlInfo;
import com.hpin.assistant.job.AttachementMailJobParameter;
import com.hpin.assistant.management.StatusManagement;
import com.hpin.assistant.service.schedule.ScheduleWrapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

/**
 * @author huaiku
 * @date 2019年1月21日
 */
@SpringBootApplication
@Configuration
@Import(DynamicDataSourceRegister.class)
@EnableTransactionManagement
public class AssistantApplication {
    private static Logger logger = LoggerFactory.getLogger("mail-schedule...");

    public static void main(String[] args) {
        SpringApplication.run(AssistantApplication.class, args);
    }

    @Bean
    CommandLineRunner testApplicationContext(ScheduleWrapperService wrapperService) {
        return args -> {
           /* personService.queryPerson(DataSourceKeyStore.DefaultDataSource);
            logger.info("开始查询另一个城市..");
            personService.queryPerson(DataSourceKeyStore.NanJingDataSource);
            MailInfo mailInfo = new MailInfo();
            mailInfo.setMessage("这是测试邮件，测试我发邮件是否能成功哈哈哈哈哈哈...");
            mailInfo.setTo(new String[]{"2293987337@qq.com"});
            mailInfo.setCc(new String[] {"2739976952@qq.com","banboll@outlook.com"});
            mailInfo.setFrom("Huaiku<"+mailProperties.getUsername()+">");
            mailInfo.setSubject("邮件测试..");
            mailService.notification(mailInfo);

            //            for (int i = 0; i < 2; i++) {
//                AttachementMailJobParameter param = new AttachementMailJobParameter();
//                StatusManagement status  = new StatusManagement();
//                param.setJobName("Job"+i);
//                param.setJobGroup("taskGroupOne");
//                param.setCronExpression("0/5 * * * * ?");
//                param.setDescription("Execute job " + i + " every 5 seconds ...");
//                scheduleTaskService.addTask(param,status);
//
//                if (status.isDone()) {
//                    System.out.println("添加任务成功..."+i);
//                }
//            }

            */
            AttachementMailJobParameter parameter = new AttachementMailJobParameter();
            parameter.setCityCode(DataSourceKeyStore.DefaultDataSource);
            parameter.setJobName("邮件列表发送");
            parameter.setJobGroup("mailList");
            parameter.setCronExpression("0 20 17 * * ?");
            parameter.setDescription("这是最近系统添加的邮件列表，请查收");
            parameter.setCityCode("defaultDataSource");
            MailInfo info = new MailInfo();
            info.setMessage("这是测试邮件，测试我发邮件是否能成功哈哈哈哈哈哈...");
            info.setSendList("2293987337@qq.com");
            info.setCopyList("2739976952@qq.com;banboll@outlook.com");
            info.setFromAddress("Huaiku<yangjun2@5i5j.com>");
            info.setSubject("邮件测试..");
            info.setCreateDate(new Date());
            parameter.setMailInfo(info);
            TaskBindSqlInfo sqlInfo = new TaskBindSqlInfo();
            sqlInfo.setSqlText(new StringBuilder("select ")
                    .append(" 	`subject` as 主题,")
                    .append(" 	`from` as 邮件发送者,")
                    .append(" 	message as 内容信息,")
                    .append(" 	copyList as 抄送对象,")
                    .append(" 	sendList as 发送对象")
                    .append(" from qrtz_task_mail_info").toString());
            sqlInfo.setCreateDate(new Date());
            sqlInfo.setFileName("邮件发送列表信息");
            parameter.setSqlInfo(sqlInfo);
            StatusManagement statusManagement = new StatusManagement();
            wrapperService.addTask(parameter,statusManagement);
        };
    }
}

