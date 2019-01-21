package com.hpin.assistant.aop;

import com.hpin.assistant.bootstrap.DataSourceKeyStore;
import com.hpin.assistant.bootstrap.RoutingDataSourceContext;
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

    @Pointcut("execution(* com.hpin.assistant.service.*Service.query*(..)) && args(cityKey,..)")
    private void pickRoutingDataSource(String cityKey) {
    }

    @Pointcut("execution(* com.hpin.assistant.service.schedule.ScheduleTaskService.*(..))")
    private void scheduleDefaultDataSource(){};

    @Around("pickRoutingDataSource(cityKey)")
    public Object defautlTransactionSetting(ProceedingJoinPoint joinPoint, String cityKey) {
        try (RoutingDataSourceContext holder = new RoutingDataSourceContext(cityKey)) {
            return joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Around("scheduleDefaultDataSource()")
    public Object scheduleDefaultDataSourceSetting(ProceedingJoinPoint joinPoint) {
        try (RoutingDataSourceContext holder = new RoutingDataSourceContext(DataSourceKeyStore.DefaultDataSource)) {
            return joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
