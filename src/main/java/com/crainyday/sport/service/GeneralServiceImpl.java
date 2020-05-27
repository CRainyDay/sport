package com.crainyday.sport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crainyday.sport.data.ApplyUser;
import com.crainyday.sport.data.GradeUser;
import com.crainyday.sport.data.MatchUser;
import com.crainyday.sport.data.School;
import com.crainyday.sport.data.SportsmanInfo;
import com.crainyday.sport.entity.Apply;
import com.crainyday.sport.entity.Event;
import com.crainyday.sport.entity.General;
import com.crainyday.sport.entity.Grade;
import com.crainyday.sport.entity.Team;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.exception.ApplyException;
import com.crainyday.sport.exception.GeneralException;
import com.crainyday.sport.mapper.ApplyMapper;
import com.crainyday.sport.mapper.EventMapper;
import com.crainyday.sport.mapper.GeneralMapper;
import com.crainyday.sport.mapper.GradeMapper;
import com.crainyday.sport.mapper.MatchMapper;
import com.crainyday.sport.mapper.RefereeMapper;
import com.crainyday.sport.mapper.TeamMapper;
import com.crainyday.sport.mapper.UserMapper;
import com.crainyday.sport.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class GeneralServiceImpl implements GeneralService {
	@Autowired
	private GeneralMapper generalMapper;
	@Autowired
	private EventMapper eventMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ApplyMapper applyMapper;
	@Autowired
	private RefereeMapper refereeMapper;
	@Autowired
	private TeamMapper teamMapper;
	@Autowired
	private MatchMapper matchMapper;
	@Autowired
	private GradeMapper gradeMapper;
	/**
	 * 获取当前所有的学校信息
	 */
	public List<School> getSchools() {
		return userMapper.getSchools();
	}
	/**
	 * 普通用户认证
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void generalIdentify(General general) throws Exception {
		// 获取管理员之前导入的普通用户ID, 若存在则绑定身份
		General exist = generalMapper.getGeneralByIdentity(general.getIdentity());
		if(exist == null) {
			throw new GeneralException("管理员尚未导入");
		}
		// 当前用户 身份唯一标识 已被绑定
		if(exist.getUserId() != null) {
			throw new GeneralException("身份已被绑定");
		}
		if(!exist.equals(general)) {
			throw new GeneralException("信息不一致");
		}
		// 若该用户是某个运动会的裁判, 则绑定裁判身份
		// 认证裁判信息
		Integer userType = 1;
		List<Integer> refereeIds = refereeMapper.getRefereeIdsByIdentity(general.getIdentity());
		if(refereeIds != null&&refereeIds.size() >0) {
			refereeMapper.batchUpdateReferee(refereeIds, general.getUserId(), general.getGeneralPhone());
			userType = -2;
		}
		// 绑定用户身份
		general.setGeneralId(exist.getGeneralId());
		Integer gender = general.getGeneralGender();
		if(gender == null||gender == 0) {
			gender = exist.getGeneralGender();
		}
		general.setGeneralGender(gender);
		generalMapper.updateGeneral(general);
		// 更新用户类型
		User user = new User();
		user.setUserId(general.getUserId());
		user.setUserType(userType);
		user.setAdminId(exist.getAdminId());
		user.setGender(gender);
		userMapper.updateUser(user);
	}
	/**
	 * 高级用户认证:
	 * 		某个代表队(学院)的负责人认证
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void collegeIdentify(General general) throws Exception {
		// 获取管理员之前导入的普通用户ID, 若存在则绑定身份
		General exist = generalMapper.getGeneralByIdentity(general.getIdentity());
		if(exist == null) {
			throw new GeneralException("管理员尚未导入");
		}
		// 当前用户 身份唯一标识 已被绑定
		if(exist.getUserId() != null) {
			throw new GeneralException("身份已被绑定");
		}
		if(!general.getGeneralCollege().equals(exist.getGeneralCollege())) {
			throw new GeneralException("信息不一致");
		}
		general.setGeneralId(exist.getGeneralId());
		generalMapper.updateGeneral(general);
		// 更新用户类型
		User user = new User();
		user.setUserId(general.getUserId());
		user.setAdminId(exist.getAdminId());
		user.setUserType(2);
		userMapper.updateUser(user);
	}
	/**
	 * 获取用户认证的学号/工号
	 */
	public String getIdentityByUserId(Integer userId) {
		return generalMapper.getIdentityByUserId(userId);
	}
	/**
	 * 普通用户报名
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.REPEATABLE_READ,
			   readOnly = false)
	public synchronized void applyEvent(Apply apply) throws Exception {
		if(eventMapper.judgeEventFull(apply.getEventId())) {
			throw new ApplyException("人数已满");
		}
		if(applyMapper.judgeApply(apply.getUserId(), apply.getEventId())) {
			throw new ApplyException("重复报名");
		}
		Event event = new Event();
		event.setEventId(apply.getEventId());
		event.setEventApply(1);
		eventMapper.updateEvent(event);
		applyMapper.addApply(apply);
	}
	/**
	 * 普通用户取消报名
	 */
	public synchronized void cancelApply(Integer userId, Integer eventId) throws Exception {
		if(applyMapper.delApply(userId, eventId)) {
			Event event = new Event();
			event.setEventId(eventId);
			event.setEventApply(-1);
			eventMapper.updateEvent(event);
		}else {
			throw new ApplyException("取消失败");
		}
	}
	/**
	 * 团队报名
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.REPEATABLE_READ,
			   readOnly = false)
	public synchronized void applyTeam(Team team, List<String> ids) throws Exception {
		if(teamMapper.judgeApply(team.getUserId(), team.getEventId())) {
			throw new ApplyException("重复报名");
		}
		if(eventMapper.judgeEventFull(team.getEventId())) {
			throw new ApplyException("人数已满");
		}
		List<Integer> userIds = generalMapper.getUserIds(ids);
		if(ids.size()!=userIds.size()) {
			throw new ApplyException("身份标识错误或用户尚未认证");
		}
		String users = JsonUtil.List2Json(userIds);
		team.setUserIds(users);
		Event event = new Event();
		event.setEventId(team.getEventId());
		event.setEventApply(1);
		eventMapper.updateEvent(event);
		teamMapper.applyTeam(team);
		Apply apply = new Apply();
		apply.setEventId(team.getEventId());
		for (Integer userId : userIds) {
			apply.setUserId(userId);
			applyMapper.addApply(apply);
		}
	}
	/**
	 * 高级用户(单位/学院)取消报名
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void cancelApplyTeam(Integer userId, Integer eventId) throws Exception {
		String userIdsJson = teamMapper.getTeamUserIds(userId, eventId);
		if(teamMapper.delTeam(userId, eventId)) {
			Event event = new Event();
			event.setEventId(eventId);
			event.setEventApply(-1);
			eventMapper.updateEvent(event);
			List<Integer> userIds = JsonUtil.Json2List(userIdsJson, Integer.class);
			for (Integer id : userIds) {
				applyMapper.delApply(id, eventId);
			}
		}else {
			throw new ApplyException("取消失败");
		}
	}
	/**
	 * 获取用户的报名状态
	 */
	public boolean getApplyState(Integer userId, Integer eventId) {
		// 个人报名/团队报名, 只要有一个存在报名信息, 则重复报名.
		if(applyMapper.judgeApply(userId, eventId)||teamMapper.judgeApply(userId, eventId)) {
			return true;
		}
		return false;
	}
	/**
	 * 获取用户所有的报名信息
	 */
	public List<ApplyUser> getApplysByUserId(Integer gamesId, Integer userId, Integer userType) {
		List<Integer> eventIds =  eventMapper.getEventIdsByGamesId(gamesId);
		eventIds.add(0);
		if(userType == 2) {
			return teamMapper.getApplysByUserId(eventIds, userId);
		}
		return applyMapper.getApplysByUserId(eventIds, userId);
	}
	/**
	 * 获取用户比赛信息
	 */
	public List<MatchUser> getMatchesByGamesId(Integer userId, Integer gamesId) {
		try {
			List<Integer> eventIds =  eventMapper.getEventIdsByGamesId(gamesId);
			eventIds.add(0);
			List<Integer> matchIds = new ArrayList<Integer>();
			List<String> matches = applyMapper.getMatchesByEventIds(userId, eventIds);
			for (String matchIdsString : matches) {
				matchIds.addAll(JsonUtil.Json2List(matchIdsString, Integer.class));
			}
			matchIds.add(0);
			return matchMapper.getMatchesByMatchIds(matchIds, userId);
		} catch (JsonProcessingException e) {
		}
		return new ArrayList<MatchUser>();
	}
	/**
	 * 获取用户的所有的比赛成绩
	 */
	public List<GradeUser> getGradesByUserId(Integer gamesId, Integer page, Integer limit, Integer userId) {
		try {
			List<Integer> eventIds =  eventMapper.getEventIdsByGamesId(gamesId);
			eventIds.add(0);
			List<Integer> matchIds = new ArrayList<Integer>();
			List<String> matches = applyMapper.getMatchesByEventIds(userId, eventIds);
			for (String matchIdsString : matches) {
				matchIds.addAll(JsonUtil.Json2List(matchIdsString, Integer.class));
			}
			matchIds.add(0);
			return gradeMapper.getGradesByUserId(matchIds, userId, (page-1)*limit, limit);
		} catch (JsonProcessingException e) {
		}
		return new ArrayList<GradeUser>();
	}
	/**
	 * 获取运动员参加的比赛项目的检录信息
	 */
	public SportsmanInfo getCheckInfo(Integer userId, Integer eventId) throws Exception {
		String json = applyMapper.getMatchIds(userId, eventId);
		List<Integer> matchIds = JsonUtil.Json2List(json, Integer.class);
		matchIds.add(0);
		return generalMapper.getCheckInfo(userId, matchIds);
	}
	/**
	 * 设置检录信息正确备注
	 */
	public void setRightRemark(Grade grade) throws Exception {
		gradeMapper.updateGrade(grade);
	}
}
