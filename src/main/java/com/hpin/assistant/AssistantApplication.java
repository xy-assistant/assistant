package com.hpin.assistant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.hpin.assistant.service.ScheduleTaskService;

/**
 * @author huaiku
 * @date 2019年1月21日
 *
 */
@SpringBootApplication
public class AssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistantApplication.class, args);
    }

    @Bean CommandLineRunner testApplicationContext(ScheduleTaskService scheduleTaskService) {
        return args -> {
        	/*
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
            */
        };
    }
}

