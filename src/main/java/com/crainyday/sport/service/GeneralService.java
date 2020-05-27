package com.crainyday.sport.service;

import java.util.List;

import com.crainyday.sport.data.ApplyUser;
import com.crainyday.sport.data.GradeUser;
import com.crainyday.sport.data.MatchUser;
import com.crainyday.sport.data.School;
import com.crainyday.sport.data.SportsmanInfo;
import com.crainyday.sport.entity.Apply;
import com.crainyday.sport.entity.General;
import com.crainyday.sport.entity.Grade;
import com.crainyday.sport.entity.Team;

public interface GeneralService {
	/**
	 * 获取当前所有的学校信息
	 */
	public List<School> getSchools();
	/**
	 * 普通用户认证
	 */
	public void generalIdentify(General general) throws Exception ;
	/**
	 * 高级用户认证
	 */
	public void collegeIdentify(General general) throws Exception ;
	/**
	 * 获取用户认证的学号/工号
	 */
	public String getIdentityByUserId(Integer userId);
	/**
	 * 普通用户报名
	 */
	public void applyEvent(Apply apply) throws Exception ;
	/**
	 * 普通用户取消报名
	 */
	public void cancelApply(Integer userId, Integer eventId) throws Exception ;
	/**
	 * 团队报名
	 */
	public void applyTeam(Team team, List<String> ids) throws Exception ;
	/**
	 * 团队取消报名
	 */
	public void cancelApplyTeam(Integer userId, Integer eventId) throws Exception ;
	/**
	 * 获取用户的报名状态
	 */
	public boolean getApplyState(Integer userId, Integer eventId);
	/**
	 * 获取用户所有的报名信息
	 */
	public List<ApplyUser> getApplysByUserId(Integer gamesId, Integer userId, Integer userType);
	/**
	 * 获取用户将来的比赛信息
	 */
	public List<MatchUser> getMatchesByGamesId(Integer userId, Integer gamesId);
	/**
	 * 获取用户的所有的比赛成绩
	 */
	public List<GradeUser> getGradesByUserId(Integer gamesId, Integer page, Integer limit, Integer userId);
	/**
	 * 获取运动员参加的比赛项目的检录信息
	 */
	public SportsmanInfo getCheckInfo(Integer userId, Integer eventId) throws Exception ;
	/**
	 * 设置检录信息正确备注
	 */
	public void setRightRemark(Grade grade) throws Exception ;
}
