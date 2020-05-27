package com.crainyday.sport.service;

import java.util.List;
import java.util.Map;

import com.crainyday.sport.data.BestGrade;
import com.crainyday.sport.data.BriefEvent;
import com.crainyday.sport.entity.Games;
import com.crainyday.sport.entity.User;

public interface LoginService {
	/**
	 * 微信小程序登录
	 */
	public User wxLogin(String url, Map<String, String> data)throws Exception;
	/**
	 * 微信小程序用户授权成功, 更新用户信息
	 */
	public void updateUser(User user);
	/**
	 * 获取用户信息
	 */
	public User getUserByOpenid(String openid);
	/**
	 * 根据用户的adminId获取运动会信息, 若无(adminId=0)则随机获取
	 */
	public List<Games> getGamesByAdminId(Integer adminId);
	/**
	 * 根据gamesId获取运动会所有项目的简略信息
	 */
	public List<BriefEvent> getEventByGamesId(Integer gamesId, Integer page, Integer limit);
	/**
	 * 根据项目ID获取项目的十佳比赛成绩
	 */
	public List<BestGrade> getGradesByEventId(Integer eventId);
	/**
	 * 添加建议或意见
	 */
	public void addSuggestion(Integer userId, String suggestion);
}
