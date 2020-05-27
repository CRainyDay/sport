package com.crainyday.sport.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crainyday.sport.data.MatchReferee;
import com.crainyday.sport.data.SportsmanInfo;
import com.crainyday.sport.data.TeamInfo;
import com.crainyday.sport.entity.Grade;
import com.crainyday.sport.entity.Referee;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.exception.ApplyException;
import com.crainyday.sport.exception.GeneralException;
import com.crainyday.sport.mapper.EventMapper;
import com.crainyday.sport.mapper.GeneralMapper;
import com.crainyday.sport.mapper.GradeMapper;
import com.crainyday.sport.mapper.MatchMapper;
import com.crainyday.sport.mapper.RefereeMapper;
import com.crainyday.sport.mapper.TeamMapper;
import com.crainyday.sport.mapper.UserMapper;
import com.crainyday.sport.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service("refereeService")
public class RefereeServiceImpl implements RefereeService {
	@Autowired
	RefereeMapper refereeMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	private EventMapper eventMapper;
	@Autowired
	private MatchMapper matchMapper;
	@Autowired
	private GeneralMapper generalMapper;
	@Autowired
	private GradeMapper gradeMapper;
	@Autowired
	private TeamMapper teamMapper;
	/**
	 * 获取裁判监管的运动会的权限
	 */
	public Integer[] getGamesAccess(Integer userId, Integer[] gamesIds) {
		List<Integer> real = Arrays.asList(refereeMapper.getGamesIds(userId));
		Integer[] access = new Integer[gamesIds.length];
		int i = 0;
		for (Integer gamesId : gamesIds) {
			if(real.contains(gamesId)) {
				access[i] = 1;
			}else {
				access[i] = 0;
			}
			i++;
		}
		return access;
	}
	/**
	 * 取消裁判认证信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void cancelIdentity(Referee referee, Map<String, Object> map) throws Exception {
		refereeMapper.cancelIdentity(referee.getRefereeId());
		map.put("errmsg", "取消成功");
		map.put("status", 200);
		map.put("userType", -2);
		if(!refereeMapper.judgeNullReferee(referee.getUserId())) {
			// 若清空了裁判认证信息, 则置用户为 普通用户.
			User user = new User();
			user.setUserId(referee.getUserId());
			user.setUserType(1);
			userMapper.updateUser(user);
			map.put("errmsg", "裁判信息全部清空，可以报名比赛项目");
			map.put("userType", 1);
		}
	}
	/**
	 * 更新裁判认证信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public Integer updateIdentity(Referee referee) throws Exception {
		List<Integer> refereeIds = refereeMapper.getNewRefereeIds(referee.getUserId());
		if(refereeIds != null&&refereeIds.size() > 0) {
			refereeMapper.batchUpdateReferee(refereeIds, referee.getUserId(), null);
			return refereeMapper.getRefereeId(referee.getUserId(), referee.getGamesId());
		}else {
			throw new GeneralException("您不是该运动会裁判哦");
		}
	}
	/**
	 * 获取裁判ID
	 */
	public Integer getRefereeId(Integer userId, Integer gamesId) {
		return refereeMapper.getRefereeId(userId, gamesId);
	}
	/**
	 * 获取裁判监管的运动会的比赛信息
	 */
	public List<MatchReferee> getMatchesByGamesId(Integer refereeId, Integer gamesId) {
		List<Integer> eventIds =  eventMapper.getEventIdsByGamesId(gamesId);
		eventIds.add(0);
		return matchMapper.getMatchesByReferee(refereeId, eventIds);
	}
	/**
	 * 获取参与比赛的运动员信息
	 */
	public List<SportsmanInfo> getSportsmanInfo(Integer matchId) {
		String userIdsJson = matchMapper.getUserIdsByMatchId(matchId);
		try {
			List<Integer> userIds = JsonUtil.Json2List(userIdsJson, Integer.class);
			return generalMapper.getSportsmanInfo(userIds, matchId);
		} catch (JsonProcessingException e) {
		}
		return new ArrayList<SportsmanInfo>();
	}
	
	/**
	 * 获取参与团队比赛的团队信息
	 */
	public List<TeamInfo> getTeamInfo(Integer matchId) {
		String userIdsJson = matchMapper.getUserIdsByMatchId(matchId);
		try {
			List<Integer> userIds = JsonUtil.Json2List(userIdsJson, Integer.class);
			return generalMapper.getTeamInfo(userIds);
		} catch (JsonProcessingException e) {
		}
		return new ArrayList<TeamInfo>();
	}
	/**
	 * 设置检录运动员的成绩备注
	 */
//	public void setCheckRemark(Grade grade) {
//		grade.setGradeScore(0D);
//		gradeMapper.addGradeReamrk(grade);
//	}
	/**
	 * 更新比赛成绩信息
	 */
	public void updateGrade(Grade grade) {
		gradeMapper.updateGrade(grade);
	}
	/**
	 * 录入比赛成绩
	 */
	public void enterGrade(Grade grade) throws Exception {
		if(!gradeMapper.updateGrade(grade)) {
			throw new ApplyException("重复录入");
		}
	}
	/**
	 * 录入团队成绩
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void enterTeamGrade(Integer applyId, Grade grade) throws Exception {
		String userIdsJson = teamMapper.getUserIdsByApplyId(applyId);
		List<Integer> userIds = JsonUtil.Json2List(userIdsJson, Integer.class);
		for (Integer userId : userIds) {
			grade.setUserId(userId);
			if(!gradeMapper.updateGrade(grade)) {
				throw new ApplyException("重复录入");
			}
		}
		if(!teamMapper.updateTeamScore(applyId, grade.getGradeScore())){
			throw new ApplyException("重复录入");
		}
	}
}
