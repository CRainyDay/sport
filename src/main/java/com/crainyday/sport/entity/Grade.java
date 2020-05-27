package com.crainyday.sport.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 对应数据库的grade数据表
 * 比赛成绩表
 * @author crainyday
 *
 */
public class Grade {
	private Integer gradeId;
	// 比赛ID
	private Integer matchId;
	// 用户ID
	private Integer userId;
	// 比赛结果
	private String gradeResult;
	// 比赛排名
	private Integer gradeRanking;
	// 比赛得分
	private Double gradeScore;
	// 成绩备注
	private String gradeRemark;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getGradeResult() {
		return gradeResult;
	}
	public void setGradeResult(String gradeResult) {
		this.gradeResult = gradeResult;
	}
	public Integer getGradeRanking() {
		return gradeRanking;
	}
	public void setGradeRanking(Integer gradeRanking) {
		this.gradeRanking = gradeRanking;
	}
	public Double getGradeScore() {
		return gradeScore;
	}
	public void setGradeScore(Double gradeScore) {
		this.gradeScore = gradeScore;
	}
	public String getGradeRemark() {
		return gradeRemark;
	}
	public void setGradeRemark(String gradeRemark) {
		this.gradeRemark = gradeRemark;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
}
