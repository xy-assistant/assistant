package com.hpin.assistant;

import com.hpin.assistant.bootstrap.DataSourceKeyStore;
import com.hpin.assistant.bootstrap.DynamicDataSourceContextHolder;
import com.hpin.assistant.bootstrap.DynamicDataSourceRegister;
import com.hpin.assistant.job.AttachementMailJobParameter;
import com.hpin.assistant.management.StatusManagement;
import com.hpin.assistant.service.PersonService;
import com.hpin.assistant.service.schedule.ScheduleTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author huaiku
 * @date 2019年1月21日
 *
 */
@Import(DynamicDataSourceRegister.class)
@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AssistantApplication {
    private static Logger logger = LoggerFactory.getLogger("mail-schedule...");

    public static void main(String[] args) {
        SpringApplication.run(AssistantApplication.class, args);
    }

    @Bean CommandLineRunner testApplicationContext(Map<String, DataSource> dataSourceMap, ScheduleTaskService scheduleTaskService, PersonService personService) {
        return args -> {

            dataSourceMap.entrySet().forEach((entry)->logger.info("数据源：{},{}",entry.getKey(),entry.getValue()));
            //DynamicDataSourceContextHolder.active(DataSourceKeyStore.DefaultDataSource);
            personService.queryPerson(DataSourceKeyStore.DefaultDataSource);
            logger.info("开始查询另一个城市..");
           // DynamicDataSourceContextHolder.active(DataSourceKeyStore.NanJingDataSource);
            personService.queryPerson(DataSourceKeyStore.NanJingDataSource);
        };
    }
}

