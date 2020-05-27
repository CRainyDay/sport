package com.crainyday.sport.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 普通用户查看的比赛成绩
 * @author crainyday
 *
 */
public class GradeUser {
	private Integer gradeId;
	private String eventName;
	private Integer matchGroup;
	private String matchType;
	private Integer matchNum;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date matchTime;
	private String gradeResult;
	private Integer gradeRanking;
	private String gradeRemark;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public Integer getMatchNum() {
		return matchNum;
	}
	public void setMatchNum(Integer matchNum) {
		this.matchNum = matchNum;
	}
	public Date getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	public Integer getMatchGroup() {
		return matchGroup;
	}
	public void setMatchGroup(Integer matchGroup) {
		this.matchGroup = matchGroup;
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
	public String getGradeRemark() {
		return gradeRemark;
	}
	public void setGradeRemark(String gradeRemark) {
		this.gradeRemark = gradeRemark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
