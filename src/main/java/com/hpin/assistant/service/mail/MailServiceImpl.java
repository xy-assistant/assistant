package com.hpin.assistant.service.mail;

import com.hpin.assistant.domain.MailInfo;
import com.hpin.assistant.management.StatusManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * @author huaiku
 * @date 2019年1月22日
 * @desc 发送邮件组件
 *
 */
@Service
public class MailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public StatusManagement notification(MailInfo info) {

        StatusManagement status;
        MimeMessage message = this.mailSender.createMimeMessage();
        try {
            this.wired(new MimeMessageHelper(message, true), info);
            this.mailSender.send(message);
            logger.info("发送邮件成功,信息摘要：{}", info.toString());
            status = new StatusManagement(true,info.toString());
        } catch (MessagingException e) {
            logger.error("邮件发送失败，失败原因：{}，信息摘要：{}", e.getMessage(),info.toString());
            status = new StatusManagement(false,e.getMessage(),info.toString());
        }

        return status;
    }

    /**
     * i. 装载信息
     *
     * @param helper
     * @param info
     */
    private void wired(MimeMessageHelper helper, MailInfo info) throws MessagingException {
        // 基本信息装载
        helper.setTo(info.getTo());
        helper.setFrom(info.getFrom());
        helper.setSubject(info.getSubject());
        helper.setText(info.getMessage());
        helper.setCc(info.getCc());
        // 附件信息装载
        if (Objects.nonNull(info.getAttachments())) {
            info.getAttachments().forEach((k,v) -> {
                FileSystemResource dataResource = new FileSystemResource(v);
                try {
                    helper.addAttachment(k,dataResource);
                } catch (MessagingException e) {
                    logger.error("附件添加失败...附件名称：{}，附件地址：{}",k,v);
                    e.printStackTrace();
                }
            });
        }
    }
}
