package com.crainyday.sport.service;

public interface EmailService {
	/**
	 * 发送邮件
	 */
	public void sendMailSimple(String to, String nickname, String code) throws Exception;
}
