package com.crainyday.sport.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crainyday.sport.data.ApplyUser;
import com.crainyday.sport.data.GradeUser;
import com.crainyday.sport.data.MatchUser;
import com.crainyday.sport.data.School;
import com.crainyday.sport.data.SportsmanInfo;
import com.crainyday.sport.entity.Apply;
import com.crainyday.sport.entity.General;
import com.crainyday.sport.entity.Grade;
import com.crainyday.sport.entity.Team;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.service.GeneralService;
/**
 * /getSchools:				获取当前所有的学校信息
 * /generalIdentify:		普通用户认证
 * /collegeIdentify:		高级用户(某个代表队/学院的负责人)认证
 * /getIdentityByUserId:	获取用户认证的学号/工号
 * 
 * /getApplyState:			根据项目ID获取用户项目报名状态
 * /applyEvent:				普通用户报名
 * /cancelApply:			普通用户取消报名
 * /applyTeam:				团队报名
 * /cancelApplyTeam:		团队取消报名
 * 
 * /getApplysByUserId:		获取用户所有的报名信息
 * /getMatchesByUserId:		获取用户将来的比赛信息
 * /getGradesByUserId:		获取用户所有的比赛成绩
 * 
 * /getCheckInfo:			获取运动员参加的比赛项目的检录信息
 * /setRightRemark:			设置检录信息正确备注
 * 
 */
@ResponseBody
@Controller
public class GeneralHandler {
	@Autowired
	private GeneralService generalService;
	
	/**
	 * 获取当前所有的学校信息
	 */
	@PostMapping("/getSchools")
	public List<School> getSchools(){
		return generalService.getSchools();
	}
	
	/**
	 * 普通用户认证
	 */
	@PostMapping("/generalIdentify")
	public Map<String, Object> generalIdentify(General general,
			@RequestAttribute("user")User user)throws Exception{
		general.setUserId(user.getUserId());
		generalService.generalIdentify(general);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "认证成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 高级用户认证:
	 * 		某个代表队(学院)的负责人认证
	 */
	@PostMapping("/collegeIdentify")
	public Map<String, Object> collegeIdentify(General general,
			@RequestAttribute("user")User user)throws Exception{
		general.setUserId(user.getUserId());
		generalService.collegeIdentify(general);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "认证成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 获取用户认证的学号/工号
	 */
	@PostMapping("/getIdentityByUserId")
	public Map<String, Object> getIdentityByUserId(
			@RequestAttribute("user")User user)throws Exception{
		String identity = generalService.getIdentityByUserId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("identity", identity);
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 获取用户的报名状态
	 */
	@PostMapping("/getApplyState")
	public Map<String, Object> getApplyState(@RequestParam("eventId")Integer eventId,
			@RequestAttribute("user")User user){
		Map<String, Object> map = new HashMap<String, Object>();
		if(generalService.getApplyState(user.getUserId(), eventId)) {
			map.put("applyState", true);
		}else {
			map.put("applyState", false);
		}
		map.put("errmsg", "获取成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 普通用户报名, 注意事务的处理.
	 */
	@PostMapping("/applyEvent")
	public Map<String, Object> applyEvent(Apply apply,
			@RequestAttribute("user")User user) throws Exception {
		apply.setUserId(user.getUserId());
		generalService.applyEvent(apply);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "报名成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 普通用户取消报名
	 */
	@PostMapping("/cancelApply")
	public Map<String, Object> cancelApply(Integer eventId,
			@RequestAttribute("user")User user) throws Exception {
		generalService.cancelApply(user.getUserId(), eventId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "取消成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 普通用户取消报名
	 */
	@PostMapping("/cancelApplyTeam")
	public Map<String, Object> cancelApplyTeam(Integer eventId,
			@RequestAttribute("user")User user) throws Exception {
		generalService.cancelApplyTeam(user.getUserId(), eventId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "取消成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 团队报名
	 */
	@PostMapping("/applyTeam")
	public Map<String, Object> applyTeam(Team team,
			@RequestAttribute("user")User user,
			@RequestParam("ids")List<String> ids) throws Exception {
		team.setUserId(user.getUserId());
		generalService.applyTeam(team, ids);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "报名成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 获取用户所有的报名信息
	 */
	@PostMapping("/getApplysByUserId")
	public List<ApplyUser> getApplysByUserId(@RequestAttribute("user")User user,
			@RequestParam("gamesId")Integer gamesId,
			@RequestParam("userType")Integer userType) throws Exception {
		return generalService.getApplysByUserId(gamesId, user.getUserId(), userType);
	}
	
	/**
	 * 获取用户将来的比赛信息
	 */
	@PostMapping("/getMatchesByUserId")
	public List<MatchUser> getMatchesByUserId(@RequestAttribute("user")User user,
			@RequestParam("gamesId")Integer gamesId){
		return generalService.getMatchesByGamesId(user.getUserId(), gamesId);
	}
	
	/**
	 * 获取用户所有的比赛成绩
	 */
	@PostMapping("/getGradesByUserId")
	public List<GradeUser> getGradesByUserId(@RequestAttribute("user")User user,
			@RequestParam("gamesId")Integer gamesId,
			@RequestParam("page")Integer page,
			@RequestParam("limit")Integer limit){
		return generalService.getGradesByUserId(gamesId, page, limit, user.getUserId());
	}
	

	/**
	 * 获取运动员参加的比赛项目的检录信息
	 */
	@PostMapping("/getCheckInfo")
	public SportsmanInfo getCheckInfo(
			@RequestParam("eventId")Integer eventId,
			@RequestAttribute("user")User user) throws Exception {
		return generalService.getCheckInfo(user.getUserId(), eventId);
	}
	
	/**
	 * 设置检录信息正确备注
	 */
	@PostMapping("setRightRemark")
	public Map<String, Object> setRightRemark(Grade grade) throws Exception {
		generalService.setRightRemark(grade);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "检录成功");
		map.put("status", 200);
		return map;
	}
}
