package com.hpin.assistant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author huaiku
 */
@Repository
public class TaskRepository {
    @Autowired
    JdbcTemplate template;

    public boolean test() {
        return this.template.queryForObject("select 1=1",Integer.class)==null;
    }
}
