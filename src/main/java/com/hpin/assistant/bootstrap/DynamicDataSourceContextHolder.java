package com.hpin.assistant.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huaiku
 * @Date: 2018/8/15 10:49
 * @Description: 数据源上下文
 */
public class DynamicDataSourceContextHolder {

    /**
     * 存储已经注册的数据源的key
     */
    public static List<String> dataSourceIds = new ArrayList<>();


    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static String getDataSourceRouterKey() {
        return HOLDER.get() == null ? DataSourceKeyStore.DefaultDataSource : HOLDER.get();
    }

    public static void active(String dataSourceRouterKey) {
        removeDataSourceRouterKey();
        HOLDER.set(dataSourceRouterKey);
    }

    /**
     * 设置数据源之前一定要先移除
     */
    public static void removeDataSourceRouterKey() {
        HOLDER.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }

    /**
     * 数据源城市对应关系
     */
    private static Map<String, String> CITY_SOURCE_MAP = new HashMap<>();

    public static Map<String, String> cityDataSourceMap() {
        if (CITY_SOURCE_MAP.isEmpty()) {
            dataSourceIds.forEach(key -> {

                if (key.toLowerCase().contains("taiyuan")) {
                    CITY_SOURCE_MAP.put(key, "太原");

                } else if (key.toLowerCase().contains("wuhan")) {
                    CITY_SOURCE_MAP.put(key, "武汉");

                } else if (key.toLowerCase().contains("suzhou")) {
                    CITY_SOURCE_MAP.put(key, "苏州");

                } else if (key.toLowerCase().contains("nanjing")) {
                    CITY_SOURCE_MAP.put(key, "南京");

                } else if (key.toLowerCase().contains("zhengzhou")) {
                    CITY_SOURCE_MAP.put(key, "郑州");

                } else if (key.toLowerCase().contains("tianjin")) {
                    CITY_SOURCE_MAP.put(key, "天津");

                } else if (key.toLowerCase().contains("chengdu")) {
                    CITY_SOURCE_MAP.put(key, "成都");

                } else if (key.toLowerCase().contains("default")) {
                    // 北京
                    CITY_SOURCE_MAP.put(key, "北京");
                }
            });
        }
        return CITY_SOURCE_MAP;
    }
}
