package com.hpin.assistant.service;

import com.hpin.assistant.dao.FileExportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 导表 service
 */
@Service
public class FileExportService implements DynamicSourceService{
    @Autowired
    FileExportDao fileExportDao;

    @Override
    public <T> T query(String cityCode) {
        return null;
    }

    @Override
    public List<Object[]> query(String cityCode, final List<String> titles, String sqlText) {
        return fileExportDao.queryExportData(titles,sqlText);
    }

}
