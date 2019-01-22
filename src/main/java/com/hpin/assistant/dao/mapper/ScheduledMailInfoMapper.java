package com.hpin.assistant.dao.mapper;

import com.hpin.assistant.domain.MailInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc mailinfo mapper
 */
@Mapper
public interface ScheduledMailInfoMapper {
    Integer insertMainInfo(MailInfo info);
}
