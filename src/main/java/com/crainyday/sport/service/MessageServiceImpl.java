package com.crainyday.sport.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crainyday.sport.utils.WeChatUtil;
import com.crainyday.sport.wechat.ApplyMessage;
import com.crainyday.sport.wechat.MatchBegin;
import com.crainyday.sport.wechat.MatchResult;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private WeChatUtil weChatUtil;
	/**
	 * 报名成功通知
	 */
	public void applySuccess(String openid, Integer gamesId, ApplyMessage message) throws Exception {
		message.setTip("可以在报名截止之前取消报名哦");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("touser", openid);
		data.put("template_id", "QHM6ZFhkxU9NLYl1_-XCS1PtZvsGbjeoS6ytYXBNCnc");
		data.put("page", "index/manage/apply/apply?gamesState=0&gamesId="+gamesId);
		weChatUtil.sendSubscribeMessage(data, message);
	}
	/**
	 * 取消报名通知
	 */
	public void applyCancel(String openid, ApplyMessage message)  throws Exception{
		message.setTip("可以在报名截止之前再次报名哦");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("touser", openid);
		data.put("template_id", "zcXrzDfc97KGBtduaCnHmBkEiNPZfUSiz_aN5JFiQOM");
		data.put("page", "index/index?openid=null");
		weChatUtil.sendSubscribeMessage(data, message);
	}
	/**
	 * 比赛开始通知
	 */
	public void matchBegin(String openid, MatchBegin message) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("touser", openid);
		data.put("template_id", "y4depWCosMgBGQpRxuWix8X0ei461pO8Ec2rxOprSGg");
		data.put("page", "index/index?openid=null");
		weChatUtil.sendSubscribeMessage(data, message);
	}
	/**
	 * 比赛结果通知
	 */
	public void matchResult(String openid, Integer gamesId, MatchResult message) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("touser", openid);
		data.put("template_id", "_PfbNfWbn6u2c1JwjVBeBLGcIiDggqe8XgWvKQoDn2o");
		data.put("page", "index/manage/grade/grade?gamesState=-1&gamesId="+gamesId);
		weChatUtil.sendSubscribeMessage(data, message);
	}
	
}
