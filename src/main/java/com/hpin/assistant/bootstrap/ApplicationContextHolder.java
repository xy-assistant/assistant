package com.hpin.assistant.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 上下文持有对象
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    public static ApplicationContext context;

    /**
     * i. 根据类型和名称获取spring bean
     * @param beanName
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName,Class<T> t) {
        return context.getBean(beanName,t);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        System.out.println("这次注册成功了把？..");
    }
}
