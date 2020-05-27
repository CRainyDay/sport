package com.crainyday.sport.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 对应数据库的apply数据表
 * 用户报名表
 * @author crainyday
 *
 */
public class Apply {
	// 报名ID
	private Integer applyId;
	// 微信小程序用户ID
	private Integer userId;
	// 运动会项目ID
	private Integer eventId;
	// 由用户报名信息和运动会项目生成的比赛IDs
	private String matchIds;
	// 用户报名时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getMatchIds() {
		return matchIds;
	}
	public void setMatchIds(String matchIds) {
		this.matchIds = matchIds;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
