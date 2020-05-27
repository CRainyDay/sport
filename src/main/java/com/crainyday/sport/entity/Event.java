package com.crainyday.sport.entity;

/**
 * 对应数据库的event数据表
 * 运动会项目表
 * @author crainyday
 *
 */
public class Event {
	// 项目ID
	private Integer eventId;
	// 项目所属运动会ID
	private Integer gamesId;
	// 项目名字
	private String eventName;
	// 项目介绍
	private String eventDesc;
	// 项目规则
	private String eventRule;
	// 人数限制
	private Integer eventNum;
	// 已报名人数
	private Integer eventApply;
	// 本轮参赛人数
	private Integer eventMatch;
	// 记录成绩
	private String recordGrade;
	// 记录保持者姓名
	private String recordName;
	// 所属单位/学院
	private String recordCollege;
	// 创记录的运动会
	private String recordGames;
	// 记录时间地点
	private String recordTp;
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getGamesId() {
		return gamesId;
	}
	public void setGamesId(Integer gamesId) {
		this.gamesId = gamesId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getEventRule() {
		return eventRule;
	}
	public void setEventRule(String eventRule) {
		this.eventRule = eventRule;
	}
	public Integer getEventNum() {
		return eventNum;
	}
	public void setEventNum(Integer eventNum) {
		this.eventNum = eventNum;
	}
	public Integer getEventApply() {
		return eventApply;
	}
	public void setEventApply(Integer eventApply) {
		this.eventApply = eventApply;
	}
	public Integer getEventMatch() {
		return eventMatch;
	}
	public void setEventMatch(Integer eventMatch) {
		this.eventMatch = eventMatch;
	}
	public String getRecordGrade() {
		return recordGrade;
	}
	public void setRecordGrade(String recordGrade) {
		this.recordGrade = recordGrade;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getRecordCollege() {
		return recordCollege;
	}
	public void setRecordCollege(String recordCollege) {
		this.recordCollege = recordCollege;
	}
	public String getRecordGames() {
		return recordGames;
	}
	public void setRecordGames(String recordGames) {
		this.recordGames = recordGames;
	}
	public String getRecordTp() {
		return recordTp;
	}
	public void setRecordTp(String recordTp) {
		this.recordTp = recordTp;
	}
}
