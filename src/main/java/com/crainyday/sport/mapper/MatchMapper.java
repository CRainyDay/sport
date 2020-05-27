package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.MatchReferee;
import com.crainyday.sport.data.MatchUser;
import com.crainyday.sport.entity.Match;

public interface MatchMapper {
	/**
	 * 添加比赛
	 */
	public void addMatch(Match match);
	/**
	 * 根据项目ID获取项目生成的所有比赛IDs
	 */
	public List<Integer> getMatchIdsByEventId(@Param("eventId")Integer eventId);
	/**
	 * 根据裁判ID获取监管的比赛信息
	 */
	public List<MatchReferee> getMatchesByReferee(@Param("refereeId")Integer refereeId, @Param("eventIds")List<Integer> eventIds);
	/**
	 * 获取某项目的所有比赛信息
	 */
	public List<MatchReferee> getMatchesByEventId(@Param("eventId")Integer eventId, @Param("page")Integer page, @Param("limit")Integer limit);
	/**
	 * 根据比赛ID获取比赛的用户IDs
	 */
	public String getUserIdsByMatchId(@Param("matchId")Integer matchId);
	/**
	 * 根据比赛IDs获取用户的比赛信息
	 * @param userId 
	 */
	public List<MatchUser> getMatchesByMatchIds(@Param("matchIds")List<Integer> matchIds, @Param("userId")Integer userId);
	/**
	 * 更新比赛时间
	 */
	public void updateMatchTime(Match match);
	/**
	 * 判断项目上一阶段的比赛是否全部完成
	 */
	public Boolean judgeCreatMatches(@Param("eventId")Integer eventId);
	/**
	 * 获取某一项目最新的比赛IDs, 用于检录信息.
	 */
	public List<Integer> getEventNewestMatchIds(@Param("eventId")Integer eventId);
}
