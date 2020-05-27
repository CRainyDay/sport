package com.crainyday.sport.data;
/**
 * 对应数据库的games数据表
 * 运动会的简略信息
 * @author crainyday
 *
 */
public class BriefGames {
	private Integer gamesId;
	private String gamesName;
	public Integer getGamesId() {
		return gamesId;
	}
	public void setGamesId(Integer gamesId) {
		this.gamesId = gamesId;
	}
	public String getGamesName() {
		return gamesName;
	}
	public void setGamesName(String gamesName) {
		this.gamesName = gamesName;
	}
}
