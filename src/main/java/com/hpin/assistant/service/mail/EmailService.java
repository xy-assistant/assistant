package com.hpin.assistant.service.mail;

import com.hpin.assistant.domain.MailInfo;
import com.hpin.assistant.management.StatusManagement;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 邮件通知
 */
public interface EmailService {
    /**
     * 邮件通知
     * @param info 通知信息
     */
    StatusManagement notification(MailInfo info);
}
