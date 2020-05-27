package com.crainyday.sport.service;

import com.crainyday.sport.wechat.ApplyMessage;
import com.crainyday.sport.wechat.MatchBegin;
import com.crainyday.sport.wechat.MatchResult;

public interface MessageService {
	/**
	 * 报名成功通知
	 */
	public void applySuccess(String openid, Integer gamesId, ApplyMessage message)  throws Exception;
	/**
	 * 取消报名通知
	 */
	public void applyCancel(String openid, ApplyMessage message) throws Exception;
	/**
	 * 比赛开始通知
	 */
	public void matchBegin(String openid, MatchBegin matchBegin) throws Exception;
	/**
	 * 比赛结果通知
	 */
	public void matchResult(String openid, Integer gamesId, MatchResult message) throws Exception;
}
