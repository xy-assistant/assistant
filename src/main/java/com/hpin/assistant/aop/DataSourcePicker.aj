package com.hpin.assistant.aop;

import com.hpin.assistant.bootstrap.DataSourceKeyStore;
import com.hpin.assistant.bootstrap.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author huaiku
 * @date 2019年1月21日
 * @desc 数据源选择
 *
 */
@Aspect
@Component
public class DataSourcePicker {
    private static final Logger logger = LoggerFactory.getLogger(DataSourcePicker.class);

    @Pointcut("execution(* com.hpin.assistant.service.*Service.*(..))")
    private void pickRoutingDataSource() {}

    @Pointcut("execution(* com.hpin.assistant.service.schedule.ScheduleTaskService.*(..))")
    private void scheduleDefaultDataSource(){}

    @Around("pickRoutingDataSource()")
    public Object othersDataSourceWired(ProceedingJoinPoint joinPoint) throws Throwable {
        // FIXME 此处应急，应该该正
        String cityKey = DataSourceKeyStore.NanJingDataSource;
        logger.info("请求访问数据源：{}",cityKey);
        if(DynamicDataSourceContextHolder.dataSourceIds.contains(cityKey)) {
            DynamicDataSourceContextHolder.active(cityKey);
            return joinPoint.proceed();
        } else {
            logger.error("数据源[{}]不存在，使用默认数据源 >{}", cityKey, joinPoint.getSignature());
            return null;
        }
    }

    @Around("scheduleDefaultDataSource()")
    public Object defaultDataSourceWired(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("请求访问数据源：---------");
        DynamicDataSourceContextHolder.active(DataSourceKeyStore.DefaultDataSource);
        return joinPoint.proceed();
    }
}
