package com.hpin.assistant.aop;

import com.hpin.assistant.bootstrap.DataSourceKeyStore;
import com.hpin.assistant.bootstrap.DynamicDataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAdjustor {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAdjustor.class);

    @Before("execution(public * com.hpin.assistant..*.query*(String,..)) && args(cityCode,..)")
    public void othersDataSourceWired(String cityCode) {
        if (DynamicDataSourceContextHolder.containsDataSource(cityCode)) {
            logger.info("active {} datasource...",cityCode);
            DynamicDataSourceContextHolder.active(cityCode);
        }
    }

    @Before("execution(public * com.hpin..service..Schedule*.*(..))")
    public void taskScheduleAdjustor() {
        DynamicDataSourceContextHolder.active(DataSourceKeyStore.DefaultDataSource);
    }
}
