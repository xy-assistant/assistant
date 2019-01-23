package com.hpin.assistant.controller;

import com.hpin.assistant.bootstrap.DynamicDataSourceContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author huaiku
 * @dare 2019年1月23日
 * @desc 数据源相关
 */
@RestController
@RequestMapping("/ds")
public class DataSourceController {

    @RequestMapping(value = "/list")
    public @ResponseBody
    Map<String,String> dataSourceList() {
        return DynamicDataSourceContextHolder.cityDataSourceMap();
    }
}
