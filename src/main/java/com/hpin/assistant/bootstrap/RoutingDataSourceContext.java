package com.hpin.assistant.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huaiku
 * @date 2019年1月21日
 * @desc 数据源路由上下文
 */
public class RoutingDataSourceContext implements AutoCloseable{

    static final ThreadLocal<String> threadLocalDataSourceKeyHolder = new ThreadLocal<>();

    public RoutingDataSourceContext(String key) {
        threadLocalDataSourceKeyHolder.set(key);
    }
    /**
     * i. 获取线程绑定的datasource key
     * @return
     *
     */
    public static String getDataSourceRoutingKey() {
        String key = threadLocalDataSourceKeyHolder.get();
        return key == null ? DataSourceKeyStore.DefaultDataSource : key;
    }

    @Override
    public void close() throws Exception {
        threadLocalDataSourceKeyHolder.remove();
    }
}
