package com.hpin.assistant;

import com.hpin.assistant.bootstrap.DataSourceKeyStore;
import com.hpin.assistant.job.AttachementMailJobParameter;
import com.hpin.assistant.management.StatusManagement;
import com.hpin.assistant.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.hpin.assistant.service.schedule.ScheduleTaskService;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author huaiku
 * @date 2019年1月21日
 *
 */
@SpringBootApplication
public class AssistantApplication {
    private static Logger logger = LoggerFactory.getLogger("mail-schedule...");

    public static void main(String[] args) {
        SpringApplication.run(AssistantApplication.class, args);
    }

    @Bean CommandLineRunner testApplicationContext(Map<String, DataSource> dataSourceMap,ScheduleTaskService scheduleTaskService) {
        return args -> {

            for (int i = 0; i < 2; i++) {
                AttachementMailJobParameter param = new AttachementMailJobParameter();
                StatusManagement status  = new StatusManagement();
                param.setJobName("Job"+i);
                param.setJobGroup("taskGroupOne");
                param.setCronExpression("0/5 * * * * ?");
                param.setDescription("Execute job " + i + " every 5 seconds ...");
                scheduleTaskService.addTask(param,status);

                if (status.isDone()) {
                    System.out.println("添加任务成功..."+i);
                }
            }

            dataSourceMap.entrySet().forEach((entry)->logger.info("数据源：{},{}",entry.getKey(),entry.getValue()));

//            personService.queryPerson(DataSourceKeyStore.DefaultDataSource);
//            logger.info("开始查询另一个城市..");
//            personService.queryPerson(DataSourceKeyStore.NanJingDataSource);
        };
    }
}

