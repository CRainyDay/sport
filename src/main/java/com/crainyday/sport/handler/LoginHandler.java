package com.crainyday.sport.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crainyday.sport.data.BestGrade;
import com.crainyday.sport.data.BriefEvent;
import com.crainyday.sport.entity.Event;
import com.crainyday.sport.entity.Games;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.service.AdminService;
import com.crainyday.sport.service.LoginService;
/**
 * /wxLogin:			微信小程序用户登录
 * /testLogin:			测试时的登录接口
 * 
 * /getGamesList:		获取小程序首页运动会列表
 * /getEventByGamesId:	根据运动会ID获取运动会所有项目的简略信息
 * /getGradesByEventId:	根据项目ID获取项目的十佳比赛成绩
 * /getEventByEventId:	根据项目ID获取项目信息
 * 
 * /updateUserInfo:		微信小程序用户授权成功, 更新用户信息
 * /addSuggestion:		添加建议或意见
 * 
 * /getGames:			根据运动会ID获取运动会信息
 *
 *
 */
@ResponseBody
@Controller
public class LoginHandler {
	@Autowired
	private LoginService loginService;
	@Autowired
	private AdminService adminService;
	/**
	 * 微信小程序登录
	 */
	@PostMapping("/wxLogin")
	public User wxLogin(@RequestParam("code")String code,
										@Value("${wx.appid}")String appId,
										@Value("${wx.secret}")String secret,
										@Value("${wx.grantType}")String grantType)
	throws Exception{
		Map<String, String> data = new HashMap<String, String>();
		data.put("appid", appId);
		data.put("secret", secret);
		data.put("js_code", code);
		data.put("grant_type", grantType);
		return loginService.wxLogin("https://api.weixin.qq.com/sns/jscode2session", data);
	}
	
	/**
	 * 测试登录
	 */
	@PostMapping("/testLogin")
	public User testLogin(@RequestParam("openid")String openid){
		return loginService.getUserByOpenid(openid);
	}
	
	
	
	/**
	 * 根据用户的adminId获取运动会信息, 若无(adminId=0)则随机获取
	 * userType =-2: 为裁判.
	 * userType =-1: 为管理员用户.
	 * userType = 0: 为普通用户, 尚未进行过普通用户认证.
	 * userType = 1: 为普通认证用户.
	 * userType = 2: 为高级用户.
	 */
	@PostMapping("/getGamesList")
	public List<Games> getGamesList(@RequestAttribute("user")User user){
		return loginService.getGamesByAdminId(user.getAdminId());
	}
	
	/**
	 * 根据运动会ID获取运动会所有项目的简略信息
	 */
	@PostMapping("/getEventByGamesId")
	public List<BriefEvent> getEventByGamesId(@RequestParam("gamesId")Integer gamesId,
			@RequestParam("page")Integer page,
			@RequestParam("limit")Integer limit){
		return loginService.getEventByGamesId(gamesId, page, limit);
	}
	
	/**
	 * 根据项目ID获取项目的十佳比赛成绩
	 */
	@PostMapping("/getGradesByEventId")
	public List<BestGrade> getGradesByEventId(@RequestParam("eventId")Integer eventId){
		return loginService.getGradesByEventId(eventId);
	}
	
	/**
	 * 根据项目ID获取项目信息
	 */
	@PostMapping("/getEventByEventId")
	public Event getEventByEventId(@RequestParam("eventId")Integer eventId){
		return adminService.getEventByEventId(eventId);
	}
	
	
	
	/**
	 * 微信小程序用户授权成功, 更新用户信息
	 */
	@PostMapping("/updateUserInfo")
	public Map<String, Object> updateUserInfo(User newUser,
								@RequestAttribute("user")User user){
		newUser.setUserId(user.getUserId());
		loginService.updateUser(newUser);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "授权成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 添加建议或意见
	 */
	@PostMapping("/addSuggestion")
	public Map<String, Object> addSuggestion(@RequestParam("suggestion")String suggestion,
			@RequestAttribute("user")User user){
		loginService.addSuggestion(user.getUserId(), suggestion);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "添加成功");
		map.put("status", 200);
		return map;
	}
	
	
	
	/**
	 * 根据运动会ID获取运动会信息
	 */
	@PostMapping("/getGames")
	public Games getGames(@RequestParam("gamesId")Integer gamesId){
		return adminService.getGamesByGamesId(gamesId);
	}
}
