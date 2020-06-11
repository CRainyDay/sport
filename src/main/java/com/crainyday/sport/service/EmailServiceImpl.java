package com.crainyday.sport.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${email.default.subject}")
	private String subject;
	@Value("${email.username}")
	private String from;
	public void sendMailSimple(String to, String nickname, String code) throws Exception {
		Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)?(\\.edu\\.cn)$");
		if(!emailPattern.matcher(to).matches()) {
			throw new MessagingException("请输入教育邮箱!");
		}
		try {
			if(nickname == null) nickname = "";
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			// 用于接收邮件的邮箱
			messageHelper.setTo(to);
			// 邮件的主题
			messageHelper.setSubject(subject);
			messageHelper.setFrom(from);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			String content = "<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body>"
					+"<p style='font-size:20px;font-weight:blod;'>尊敬的管理员: "+ nickname +", 您好!</p>"
					+"<p style='text-indent:2em;font-size:20px'>欢迎使用 运动会Plus助手 小程序，您本次的验证码是: "
					+"<span style='font-size:30px; font-weight:blod; color:red;'>"+ code +"</span>"
					+", 10分钟之内有效, 请尽快使用!</p>"
					+"<span style='font-size:18px; float:right; margin-right:60px;'>"+ sdf.format(new Date()) +"</span></body></html>";
			// 邮件的正文，第二个boolean类型的参数代表html格式
			messageHelper.setText(content, true);
			// 发送
			javaMailSender.send(mimeMessage);
			LOGGER.info("Send To------{}------======Success", to);
		} catch (Exception e) {
			LOGGER.info("Send To------{}------======Failed", to);
			throw new MessagingException("邮件发送失败!", e);
		}
	}
}
