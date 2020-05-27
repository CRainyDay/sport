package com.crainyday.sport.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.BriefGames;
import com.crainyday.sport.entity.Games;

public interface GamesMapper {
	/**
	 * 获取管理员adminId创建的运动会信息
	 */
	public List<Games> getGamesByAdminId(@Param("adminId") Integer adminId);
	/**
	 * 添加一条运动会信息
	 */
	public void addGames(Games games);
	/**
	 * 修改一条运动会信息
	 */
	public void updateGames(Games games);
	/**
	 * 删除一条运动会信息
	 */
	public void delGames(@Param("gamesId")Integer gamesId);
	/**
	 * 查询一条运动会信息
	 */
	public Games getGamesByGamesId(@Param("gamesId")Integer gamesId);
	/**
	 * 获取管理员所管理的所有运动会的简略信息
	 */
	public List<BriefGames> getBriefGamesByAdminId(@Param("adminId") Integer adminId);
}
