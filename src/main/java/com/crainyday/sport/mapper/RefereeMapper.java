package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.RefereeAdmin;
import com.crainyday.sport.entity.Referee;
import com.crainyday.sport.excel.RefereeData;

public interface RefereeMapper {
	/**
	 * 根据identity查询是否有裁判
	 */
	public List<Integer> getRefereeIdsByIdentity(@Param("identity")String identity);
	/**
	 * 裁判认证时批量更新裁判信息
	 */
	public int batchUpdateReferee(@Param("refereeIds")List<Integer> refereeIds, @Param("userId")Integer userId, @Param("refereePhone")String refereePhone);
	/**
	 * 通过裁判ID获取管理员ID
	 */
	public Integer getAdminByRefereeId(@Param("refereeId")Integer refereeId);/**
	 * 更新裁判信息
	 */
	public void updateReferee(Referee referee);
	/**
	 * 将解析得到的Excel批量添加裁判信息
	 */
	public boolean addRefereeByExcel(@Param("datas")List<RefereeData> datas, @Param("gamesId")Integer gamesId);
	/**
	 * 获取裁判监管的运动会ID
	 */
	public Integer[] getGamesIds(@Param("userId")Integer userId);
	/**
	 * 获取某个运动会所有的裁判IDs
	 */
	public List<Integer> getRefereeIdsByGamesId(@Param("gamesId")Integer gamesId);
	/**
	 * 获取裁判ID
	 */
	public Integer getRefereeId(@Param("userId")Integer userId, @Param("gamesId")Integer gamesId);
	/**
	 * 获取运动会现有的裁判信息
	 */
	public List<RefereeAdmin> getRefereesByGamesId(@Param("gamesId")Integer gamesId, @Param("page")Integer page, @Param("limit")Integer limit);
	/**
	 * 删除指定裁判信息
	 */
	public void delReferee(@Param("refereeId")Integer refereeId);
	/**
	 * 获取运动会所有的裁判信息, 用于拉取运动会的裁判信息
	 */
	public List<Referee> getRefereeList(@Param("gamesId")Integer gamesId);
	/**
	 * 通过List批量添加裁判信息
	 */
	public void addRefereeByList(@Param("referees")List<Referee> referees, @Param("gamesId")Integer gamesId);
	/**
	 * 判断运动会是否有裁判信息
	 */
	public Boolean judgePull(@Param("gamesId")Integer gamesId);
	/**
	 * 取消裁判认证信息
	 */
	public void cancelIdentity(@Param("refereeId")Integer refereeId);
	/**
	 * 判断用户的裁判认证信息是否已被清空
	 */
	public boolean judgeNullReferee(@Param("userId")Integer userId);
	/**
	 * 获取要更新的裁判IDs
	 */
	public List<Integer> getNewRefereeIds(@Param("userId")Integer userId);
}
