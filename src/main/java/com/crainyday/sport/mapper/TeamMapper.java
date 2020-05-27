package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.ApplyUser;
import com.crainyday.sport.entity.Team;

public interface TeamMapper {
	/**
	 * 判断当前用户是否已报名该项目
	 */
	public boolean judgeApply(@Param("userId")Integer userId, @Param("eventId")Integer eventId);
	/**
	 * 团队报名
	 */
	public void applyTeam(Team team);
	/**
	 * 获取某一个团队的报名用户IDs信息
	 */
	public String getTeamUserIds(@Param("userId")Integer userId, @Param("eventId")Integer eventId);
	/**
	 * 取消团队报名
	 */
	public boolean delTeam(@Param("userId")Integer userId, @Param("eventId")Integer eventId);
	/**
	 * 根据项目ID获取用户的报名信息, 用于生成比赛信息/更新报名信息
	 */
	public List<Team> getApplysByEventId(@Param("eventId")Integer eventId);
	/**
	 * 根据用户IDs获取用户的报名信息, 用于生成比赛信息/更新报名信息
	 */
	public List<Team> getApplysByScore(@Param("eventId")Integer eventId, @Param("number")Integer number);
	/**
	 * 更新报名队伍的比赛IDs
	 */
	public void updateMatchIdsByTeam(Team team);
	/**
	 * 获取某一个团队的报名用户IDs信息
	 */
	public String getUserIdsByApplyId(@Param("applyId")Integer applyId);
	/**
	 * 更新团队成绩
	 */
	public Boolean updateTeamScore(@Param("applyId")Integer applyId, @Param("teamScore")Double teamScore);
	/**
	 * 获取用户所有的报名信息
	 */
	public List<ApplyUser> getApplysByUserId(@Param("eventIds")List<Integer> eventIds, @Param("userId")Integer userId);
}
