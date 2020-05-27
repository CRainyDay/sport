package com.crainyday.sport.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 对应数据库的games数据表
 * 运动会表
 * @author crainyday
 *
 */
public class Games {
	private Integer gamesId;
	private Integer adminId;
	private String gamesName;
	private String gamesOrg;
	private String gamesPlace;
	@JsonFormat(pattern = "yyyy/M/d",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date gamesBegin;
	@JsonFormat(pattern = "yyyy/M/d",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date gamesEnd;
	/**
	 * gamesState的取值:
	 * 		-1: 已结束
	 *		 0: 报名中
	 * 		 1: 进行中
	 * 		 2: 筹备中
	 */
	private String gamesState;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date applyEnd;
	public Integer getGamesId() {
		return gamesId;
	}
	public void setGamesId(Integer gamesId) {
		this.gamesId = gamesId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getGamesName() {
		return gamesName;
	}
	public void setGamesName(String gamesName) {
		this.gamesName = gamesName;
	}
	public Date getGamesBegin() {
		return gamesBegin;
	}
	public void setGamesBegin(Date gamesBegin) {
		this.gamesBegin = gamesBegin;
	}
	public Date getGamesEnd() {
		return gamesEnd;
	}
	public void setGamesEnd(Date gamesEnd) {
		this.gamesEnd = gamesEnd;
	}
	public String getGamesState() {
		Date now = new Date();
		if(now.before(applyEnd)) {
			gamesState = "0";
		}else if (now.after(applyEnd)&&now.before(gamesBegin)) {
			gamesState = "2";
		}else if(now.after(gamesBegin)&&now.before(gamesEnd)) {
			gamesState = "1";
		}else if(now.after(gamesEnd)) {
			gamesState = "-1";
		}
		return gamesState;
	}
	public String getGamesOrg() {
		return gamesOrg;
	}
	public void setGamesOrg(String gamesOrg) {
		this.gamesOrg = gamesOrg;
	}
	public String getGamesPlace() {
		return gamesPlace;
	}
	public void setGamesPlace(String gamesPlace) {
		this.gamesPlace = gamesPlace;
	}
	public Date getApplyEnd() {
		return applyEnd;
	}
	public void setApplyEnd(Date applyEnd) {
		this.applyEnd = applyEnd;
	}
}
