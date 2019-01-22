package com.hpin.assistant.service;

import org.springframework.stereotype.Service;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 导表 service
 */
@Service
public class FileExportService implements DynamicSourceService{
    @Override
    public <T> T query(String cityCode) {
        return null;
    }

    @Override
    public <T, E> T query(String cityCode, E e) {
        return null;
    }

    @Override
    public <T> T queryById(String cityCode, long id) {
        return null;
    }
}
