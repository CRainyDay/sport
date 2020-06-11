package com.crainyday.sport.wechat;

import java.util.HashMap;
import java.util.Map;

/**
 * 比赛时间更新通知
 * @author crainyday
 *
 */
public class MatchUpdate {
	private Map<String, String> thing1;
	private Map<String, String> thing2;
	private Map<String, String> character_string3;
	private Map<String, String> number4;
	private Map<String, String> time5;
	public MatchUpdate() {
		thing1 = new HashMap<String, String>();
		thing2 = new HashMap<String, String>();
		character_string3 = new HashMap<String, String>();
		number4 = new HashMap<String, String>();
		time5 = new HashMap<String, String>();
	}
	public void setEventName(String eventName) {
		thing1.put("value", eventName);
	}
	public void setMatchType(String matchType) {
		thing2.put("value", matchType);
	}
	public void setMatchGroup(String matchGroup) {
		character_string3.put("value", matchGroup);
	}
	public void setMatchNum(Integer matchNum) {
		number4.put("value", matchNum.toString());
	}
	public void setMatchTime(String matchTime) {
		time5.put("value", matchTime);
	}
	public Map<String, String> getThing1() {
		return thing1;
	}
	public Map<String, String> getThing2() {
		return thing2;
	}
	public Map<String, String> getCharacter_string3() {
		return character_string3;
	}
	public Map<String, String> getNumber4() {
		return number4;
	}
	public Map<String, String> getTime5() {
		return time5;
	}
}
