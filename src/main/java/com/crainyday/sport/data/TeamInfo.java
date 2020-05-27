package com.crainyday.sport.data;

public class TeamInfo {
	private Integer applyId;
	// 代表队
	private String generalClass;
	// 单位
	private String generalCollege;
	// 道次
	private String runway;
	private Double teamScore;
	// 团队项目参赛人数
	private Integer number = 4;
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public String getGeneralClass() {
		return generalClass;
	}
	public void setGeneralClass(String generalClass) {
		this.generalClass = generalClass;
	}
	public String getGeneralCollege() {
		return generalCollege;
	}
	public void setGeneralCollege(String generalCollege) {
		this.generalCollege = generalCollege;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getRunway() {
		return runway;
	}
	public void setRunway(String runway) {
		this.runway = runway;
	}
	public Double getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(Double teamScore) {
		this.teamScore = teamScore;
	}
}
