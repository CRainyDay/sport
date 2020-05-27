package com.crainyday.sport.service;

import java.util.List;
import java.util.Map;

import com.crainyday.sport.data.MatchReferee;
import com.crainyday.sport.data.SportsmanInfo;
import com.crainyday.sport.data.TeamInfo;
import com.crainyday.sport.entity.Grade;
import com.crainyday.sport.entity.Referee;

public interface RefereeService {
	/**
	 * 获取裁判监管的运动会的权限
	 */
	public Integer[] getGamesAccess(Integer userId, Integer[] gamesIds);
	/**
	 * 取消裁判认证信息
	 */
	public void cancelIdentity(Referee referee, Map<String, Object> map) throws Exception;
	/**
	 * 更新裁判认证信息
	 */
	public Integer updateIdentity(Referee referee)throws Exception;
	/**
	 * 获取裁判ID
	 */
	public Integer getRefereeId(Integer userId, Integer gamesId);
	/**
	 * 获取裁判监管的运动会的比赛信息
	 */
	public List<MatchReferee> getMatchesByGamesId(Integer refereeId, Integer gamesId);
	/**
	 * 获取参与比赛的运动员信息
	 */
	public List<SportsmanInfo> getSportsmanInfo(Integer matchId);
	/**
	 * 获取参与团队比赛的团队信息
	 */
	public List<TeamInfo> getTeamInfo(Integer matchId);
	/**
	 * 设置检录运动员的成绩备注
	 */
//	public void setCheckRemark(Grade grade);
	/**
	 * 更新比赛成绩信息
	 */
	public void updateGrade(Grade grade);
	/**
	 * 录入比赛成绩
	 */
	public void enterGrade(Grade grade) throws Exception;
	/**
	 * 录入团队成绩
	 */
	public void enterTeamGrade(Integer applyId, Grade grade)throws Exception ;
}
