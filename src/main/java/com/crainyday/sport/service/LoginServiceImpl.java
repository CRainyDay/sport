package com.crainyday.sport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crainyday.sport.data.BestGrade;
import com.crainyday.sport.data.BriefEvent;
import com.crainyday.sport.entity.Games;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.entity.WxSession;
import com.crainyday.sport.exception.LoginException;
import com.crainyday.sport.mapper.EventMapper;
import com.crainyday.sport.mapper.GamesMapper;
import com.crainyday.sport.mapper.GradeMapper;
import com.crainyday.sport.mapper.MatchMapper;
import com.crainyday.sport.mapper.UserMapper;
import com.crainyday.sport.utils.HTTPUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GamesMapper gamesMapper;
	@Autowired
	private EventMapper eventMapper;
	@Autowired
	private MatchMapper matchMapper;
	@Autowired
	private GradeMapper gradeMapper;
	/**
	 * 微信小程序登录
	 */
	public User wxLogin(String url, Map<String, String> data) throws Exception {
		User user  = null;
		try {
			String json = HTTPUtil.GET(url, data);
			ObjectMapper mapper = new ObjectMapper();
			WxSession wxSession = mapper.readValue(json, WxSession.class);
			String openid = wxSession.getOpenid();
			user = userMapper.getUserByOpenid(openid);
			if(user == null) {
				user = new User();
				if(openid != null && userMapper.addUserOpenid(openid)) {
					user.setOpenid(openid);
					user.setUserType(0);
				}
			}
		} catch (Exception e) {
			throw new LoginException("网络异常");
		}
		return user;
	}
	/**
	 * 微信小程序用户授权成功, 更新用户信息
	 */
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
	/**
	 * 根据小程序的openid获取用户信息
	 */
	public User getUserByOpenid(String openid) {
		return userMapper.getUserByOpenid(openid);
	}
	/**
	 * 根据用户的adminId获取运动会信息, 若无(adminId=0)则随机获取
	 */
	public List<Games> getGamesByAdminId(Integer adminId) {
		return gamesMapper.getGamesByAdminId(adminId);
	}
	/**
	 * 根据gamesId获取运动会所有项目的简略信息
	 */
	public List<BriefEvent> getEventByGamesId(Integer gamesId, Integer page, Integer limit) {
		return eventMapper.getEventByGamesId(gamesId, (page-1)*limit, limit);
	}
	/**
	 * 根据项目ID获取项目的十佳比赛成绩
	 */
	public List<BestGrade> getGradesByEventId(Integer eventId) {
		List<Integer> matchIds = matchMapper.getMatchIdsByEventId(eventId);
		matchIds.add(0);
		return gradeMapper.getBestGrades(matchIds);
	}
	/**
	 * 添加建议或意见
	 */
	public void addSuggestion(Integer userId, String suggestion) {
		userMapper.addSuggestion(userId, suggestion);
	}
}
