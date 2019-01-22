package com.hpin.assistant.dao.mapper;

import com.hpin.assistant.domain.TaskBindSqlInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduledSqlInfoMapper {
    Integer insertSqlInfo(TaskBindSqlInfo info);
}
