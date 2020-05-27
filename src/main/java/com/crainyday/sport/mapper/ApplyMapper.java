package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.ApplyUser;
import com.crainyday.sport.entity.Apply;

public interface ApplyMapper {
	/**
	 * 普通用户报名
	 */
	public void addApply(Apply apply);
	/**
	 * 判断当前用户是否已报名该项目
	 */
	public boolean judgeApply(@Param("userId")Integer userId, @Param("eventId")Integer eventId);
	/**
	 * 取消报名
	 */
	public boolean delApply(@Param("userId")Integer userId, @Param("eventId")Integer eventId);
	
	/**
	 * 根据项目ID获取用户的报名信息, 用于生成比赛信息/更新报名信息
	 */
	public List<Apply> getApplysByEventId(@Param("eventId")Integer eventId);
	/**
	 * 根据用户IDs获取用户的报名信息, 用于生成比赛信息/更新报名信息
	 */
	public List<Apply> getApplysByUserIds(@Param("userIds")List<Integer> userIds, @Param("eventId")Integer eventId);
	
	/**
	 * 批量更新报名用户的比赛id为 matchIds
	 */
	public void updateMatchIds(@Param("applys")List<Apply> applys, @Param("matchIds")String matchIds);
	/**
	 * 更新报名用户的比赛IDs
	 */
	public void updateMatchIdsByApply(Apply apply);
	
	/**
	 * 根据用户ID和项目IDs获取普通用户报名生成的比赛
	 */
	public List<String> getMatchesByEventIds(@Param("userId")Integer userId, @Param("eventIds")List<Integer> eventIds);
	/**
	 * 获取用户所有的报名信息
	 */
	public List<ApplyUser> getApplysByUserId(@Param("eventIds")List<Integer> eventIds, @Param("userId")Integer userId);
	/**
	 * 获取用户报名项目生成的比赛IDs
	 */
	public String getMatchIds(@Param("userId")Integer userId, @Param("eventId")Integer eventId);
}
