package com.hpin.assistant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author huaiku
 * @date 2019年1月21日
 *
 */
@Repository
public class PersonRepository {
    @Autowired
    JdbcTemplate template;

    public String getAddress() {
        return this.template.queryForObject("SELECT ADDRESS FROM PERSONS WHERE Id_P = 1",String.class);
    }
}
