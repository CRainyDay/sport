package com.crainyday.sport.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crainyday.sport.data.MatchReferee;
import com.crainyday.sport.data.SportsmanInfo;
import com.crainyday.sport.data.TeamInfo;
import com.crainyday.sport.entity.Grade;
import com.crainyday.sport.entity.Referee;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.service.RefereeService;
/**
 * /getGamesAccess:			获取裁判监管的运动会的权限
 * /cancelRefereeIdentity:	取消裁判认证信息
 * /updateRefereeIdentity:	更新裁判认证信息
 * 
 * /getRefereeId:			根据用户ID获取裁判ID
 * /getMatchesByGamesId:	获取裁判监管的运动会(将来的)比赛信息
 * 
 * /getSportsmanInfo:		获取参与比赛的运动员信息
 * /getTeamInfo:			获取参与团队比赛的团队信息
 * 
 * /setCheckRemark:			设置检录运动员的成绩备注(废弃)
 * /enterGrade:				录入比赛成绩
 * /enterTeamGrade:			录入团队成绩
 * 
 */

@ResponseBody
@Controller
public class RefereeHandler {
	@Autowired
	RefereeService refereeService;
	/**
	 * 获取裁判监管的运动会的权限
	 */
	@PostMapping("/getGamesAccess")
	public List<Integer> getGamesAccess(@RequestParam("gamesIds")Integer[] gamesIds,
			@RequestAttribute("user")User user) {
		return Arrays.asList(refereeService.getGamesAccess(user.getUserId(), gamesIds));
	}
	/**
	 * 取消裁判认证信息
	 */
	@PostMapping("/cancelRefereeIdentity")
	public Map<String, Object> cancelIdentity(Referee referee, 
			@RequestAttribute("user")User user) throws Exception{
		referee.setUserId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		refereeService.cancelIdentity(referee, map);
		return map;
	}
	/**
	 * 更新裁判认证信息
	 */
	@PostMapping("/updateRefereeIdentity")
	public Map<String, Object> updateIdentity(Referee referee, 
			@RequestAttribute("user")User user) throws Exception{
		referee.setUserId(user.getUserId());
		Integer refereeId = refereeService.updateIdentity(referee);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("refereeId", refereeId);
		map.put("errmsg", "更新成功");
		map.put("status", 200);
		return map;
	}
	
	
	/**
	 * 获取裁判ID
	 */
	@PostMapping("/getRefereeId")
	public Integer getRefereeId(@RequestParam("gamesId")Integer gamesId,
			@RequestAttribute("user")User user) {
		return refereeService.getRefereeId(user.getUserId(), gamesId);
	}
	
	/**
	 * 获取裁判监管的运动会的比赛信息
	 */
	@PostMapping("/getMatchesByGamesId")
	public List<MatchReferee> getMatchesByGamesId(@RequestParam("gamesId")Integer gamesId,
			@RequestParam("refereeId")Integer refereeId){
		return refereeService.getMatchesByGamesId(refereeId, gamesId);
	}
	
	
	
	/**
	 * 获取参与比赛的运动员信息
	 */
	@PostMapping("/getSportsmanInfo")
	public List<SportsmanInfo> getSportsmanInfo(@RequestParam("matchId")Integer matchId){
		return refereeService.getSportsmanInfo(matchId);
	}
	/**
	 * 获取参与团队比赛的团队信息
	 */
	@PostMapping("/getTeamInfo")
	public List<TeamInfo> getTeamInfo(@RequestParam("matchId")Integer matchId){
		return refereeService.getTeamInfo(matchId);
	}
	
	
	/**
	 * 设置检录运动员的成绩备注
	 */
//	@PostMapping("/setCheckRemark")
//	public Map<String, Object> setCheckRemark(@RequestParam("option")String option, Grade grade){
//		if(option == null||option.equals("null")) {
//			refereeService.setCheckRemark(grade);
//		}else {
//			grade.setGradeScore(null);
//			refereeService.updateGrade(grade);
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("errmsg", "检录成功");
//		map.put("status", 200);
//		return map;
//	}
	
	
	
	/**
	 * 录入比赛成绩
	 */
	@PostMapping("/enterGrade")
	public Map<String, Object> enterGrade(Grade grade) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		refereeService.enterGrade(grade);
		map.put("errmsg", "录入成功");
		map.put("status", 200);
		return map;
	}
	/**
	 * 录入团队成绩
	 */
	@PostMapping("/enterTeamGrade")
	public Map<String, Object> enterTeamGrade(Grade grade,
			@RequestParam("applyId")Integer applyId) throws Exception{
		refereeService.enterTeamGrade(applyId, grade);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "录入成功");
		map.put("status", 200);
		return map;
	}
}
