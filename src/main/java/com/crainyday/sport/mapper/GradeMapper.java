package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.BestGrade;
import com.crainyday.sport.data.GradeUser;
import com.crainyday.sport.entity.Grade;

public interface GradeMapper {
	/**
	 * 添加运动员检录信息备注
	 */
	public void addGradeReamrk(Grade grade);
	/**
	 * 添加运动员跑道信息
	 */
	public void addGradeRunway(@Param("userId")Integer userId, @Param("matchId")Integer matchId, @Param("runway")String runway);
	/**
	 * 更新比赛成绩信息
	 */
	public boolean updateGrade(Grade grade);
	/**
	 * 获取十佳比赛成绩
	 */
	public List<BestGrade> getBestGrades(@Param("matchIds")List<Integer> matchIds);
	/**
	 * 获取 比赛 ids 中成绩最好的 number 个人的IDs
	 */
	public List<Integer> getBestUserIds(@Param("matchIds")List<Integer> matchIds, @Param("number")Integer number);
	/**
	 * 获取用户的所有的比赛成绩
	 */
	public List<GradeUser> getGradesByUserId(@Param("matchIds")List<Integer> matchIds, @Param("userId")int userId, @Param("page")int page, @Param("limit")Integer limit);
}
