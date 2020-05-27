package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.SportsmanInfo;
import com.crainyday.sport.data.TeamInfo;
import com.crainyday.sport.entity.General;
import com.crainyday.sport.excel.GeneralData;

public interface GeneralMapper {
	/**
	 * 根据identity查询普通用户
	 */
	public General getGeneralByIdentity(@Param("identity")String identity);
	/**
	 * 获取用户认证的学号/工号
	 */
	public String getIdentityByUserId(@Param("userId")Integer userId);
	/**
	 * 更新普通用户信息
	 */
	public void updateGeneral(General general);
	/**
	 * 将解析得到的Excel批量添加普通用户信息
	 */
	public boolean addGeneralByExcel(@Param("datas")List<GeneralData> datas, @Param("userId")Integer userId);
	/**
	 * 取参与比赛的运动员信息
	 */
	public List<SportsmanInfo> getSportsmanInfo(@Param("userIds")List<Integer> userIds, @Param("matchId")Integer matchId);
	/**
	 * 获取待检录运动员的信息
	 */
	public SportsmanInfo getCheckInfo(@Param("userId")Integer userId, @Param("matchIds")List<Integer> matchIds);
	/**
	 * 获取参与团队比赛的团队信息
	 */
	public List<TeamInfo> getTeamInfo(@Param("userIds")List<Integer> userIds);
	/**
	 * 根据identity获取userId
	 */
	public List<Integer> getUserIds(@Param("ids")List<String> ids);
}
