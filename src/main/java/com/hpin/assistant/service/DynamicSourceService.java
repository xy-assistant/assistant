package com.hpin.assistant.service;

import java.util.List;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 动态控制数据源用
 */
public interface DynamicSourceService {
    /**
     * i. 无参数普通查询
     * @param cityCode
     * @param <T>
     * @return
     */
    <T> T query(String cityCode);

    /**
     * i. 带参查询
     * @param cityCode
     * @return
     */
    List<Object[]> query(String cityCode, final List<String> titles, String sqlText);

}
