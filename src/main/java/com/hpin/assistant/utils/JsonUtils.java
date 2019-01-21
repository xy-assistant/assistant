package com.hpin.assistant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author huaiku
 * @date 2019年1月21日
 * @desc JSON 工具
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    /**
     * i. 对象转JSON
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
