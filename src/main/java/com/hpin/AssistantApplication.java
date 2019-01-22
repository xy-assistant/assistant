package com.hpin;

import com.hpin.assistant.bootstrap.DataSourceKeyStore;
import com.hpin.assistant.bootstrap.DynamicDataSourceRegister;
import com.hpin.assistant.service.PersonService;
import com.hpin.assistant.service.schedule.ScheduleTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

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
    CommandLineRunner testApplicationContext(Map<String, DataSource> dataSourceMap, ScheduleTaskService scheduleTaskService, PersonService personService, ApplicationContext context) {
        return args -> {
            personService.queryPerson(DataSourceKeyStore.DefaultDataSource);
            logger.info("开始查询另一个城市..");
            personService.queryPerson(DataSourceKeyStore.NanJingDataSource);
        };
    }
}

