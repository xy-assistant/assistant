package com.hpin.assistant.service;

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
     * i. 带参查询，参数type = E
     * @param cityCode
     * @param e
     * @param <T>
     * @param <E>
     * @return
     */
    <T,E> T query(String cityCode,E e);

    /**
     * i. 根据ID查询
     * @param cityCode
     * @param id
     * @param <T>
     * @return
     */
    <T> T queryById(String cityCode,long id);
}
