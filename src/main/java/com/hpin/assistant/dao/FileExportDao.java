package com.hpin.assistant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author huaiku
 * @date 2019年1月21日
 *
 */
@Repository
public class FileExportDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * i. 此方法不带参数，如后期需要引入带参方法，重载一个 queryExportData 便可
     * @param titles 标题栏
     * @param sqlText 待执行sql
     * @return 根据 sql 获取的所有待导出参数
     */
    public List<Object[]> queryExportData(final List<String> titles,String sqlText) {
        return this.jdbcTemplate.query(sqlText, rs -> {
            int columnCount = 0;

            // 设置标题行
            if (Objects.nonNull(titles) && titles.size() == 0) {
                ResultSetMetaData metaData = rs.getMetaData();
                columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    titles.add(metaData.getColumnLabel(i));
                }
            }

            List<Object[]> resultArray = new ArrayList<>();
            Object[] data;

            while (rs.next()) {
                data = new Object[columnCount];
                for (int i = 0; i< columnCount ;i++) {
                    data[i] = rs.getObject(i+1);
                }
                resultArray.add(data);
            }
            return resultArray;
        });
    }
}
