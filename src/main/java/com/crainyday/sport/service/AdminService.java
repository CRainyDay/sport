package com.crainyday.sport.service;

import java.util.List;

import com.crainyday.sport.data.BriefGames;
import com.crainyday.sport.data.EventAdmin;
import com.crainyday.sport.data.MatchReferee;
import com.crainyday.sport.data.RefereeAdmin;
import com.crainyday.sport.entity.Admin;
import com.crainyday.sport.entity.Event;
import com.crainyday.sport.entity.Games;
import com.crainyday.sport.entity.Match;

public interface AdminService {
	/**
	 * 管理员认证
	 */
	public void adminIdentify(Admin admin);
	/**
	 * 管理员批量上传普通用户信息
	 */
	public void addGeneralByExcel(String filepath, Integer adminId);
	/**
	 * 管理员批量上传裁判信息
	 */
	public void addRefereeByExcel(String filepath, Integer gamesId, Integer adminId);
	/**
	 * 重置用户认证信息
	 */
	public void resetIdentity(String identity, Integer adminId) throws Exception;
	/**
	 * 拉取某个运动会的裁判信息
	 */
	public void pullReferee(Integer fromGamesId, Integer toGamesId) throws Exception;
	/**
	 * 删除指定裁判信息
	 */
	public void delReferee(Integer refereeId);
	/**
	 * 添加运动会信息
	 */
	public void addGames(Games games);
	/**
	 * 修改运动会信息
	 */
	public void updateGames(Games games);
	/**
	 * 删除运动会信息
	 */
	public void delGames(Integer gamesId) throws Exception;
	/**
	 * 查询运动会信息
	 */
	public Games getGamesByGamesId(Integer gamesId);
	/**
	 * 管理员批量上传运动会的项目信息
	 */
	public void addEventByExcel(String filepath, Integer gamesId);
	/**
	 * 拉取某个运动会的项目信息
	 */
	public void pullEvent(Integer fromGamesId, Integer toGamesId) throws Exception;
	/**
	 * 修改项目信息
	 */
	public void updateEvent(Event event);
	/**
	 * 删除项目信息
	 */
	public void delEvent(Integer eventId)  throws Exception;
	/**
	 * 获取运动会现有的裁判信息
	 */
	public List<RefereeAdmin> getRefereesByGamesId(Integer gamesId, Integer page, Integer limit);
	/**
	 * 查询项目信息
	 */
	public Event getEventByEventId(Integer eventId);
	/**
	 * 获取管理员所管理的所有运动会的简略信息
	 */
	public List<BriefGames> getBriefGamesByAdminId(Integer adminId);
	/**
	 * 生成单人项目比赛信息
	 */
	public void createMatches(Event event, String matchType) throws Exception;
	/**
	 * 生成团体项目比赛信息
	 */
	public void createTeamMatches(Event event, String matchType) throws Exception;
	/**
	 * 获取某项目的所有比赛信息
	 */
	public List<MatchReferee> getMatchesByEventId(Integer eventId, Integer page, Integer limit);
	/**
	 * 更新比赛时间
	 */
	public void updateMatchTime(Match match, String newMatchTime, String eventName) throws Exception ;
	/**
	 * 管理员获取运动会的简略项目信息.
	 */
	public List<EventAdmin> getEventAdmin(Integer gamesId, Integer page, Integer limit);
	/**
	 * 获取检录某一项目比赛的二维码
	 */
	public String getCheckQRCode(String filePath, Integer eventId) throws Exception ;
}
