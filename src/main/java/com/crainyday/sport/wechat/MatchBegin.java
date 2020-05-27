package com.crainyday.sport.wechat;

import java.util.HashMap;
import java.util.Map;

/**
 * 比赛开始提醒
 * @author crainyday
 *
 */
public class MatchBegin {
	private Map<String, String> thing1;
	private Map<String, String> thing2;
	private Map<String, String> thing3;
	private Map<String, String> thing5;
	public MatchBegin() {
		thing1 = new HashMap<String, String>();
		thing2 = new HashMap<String, String>();
		thing3 = new HashMap<String, String>();
		thing5 = new HashMap<String, String>();
	}
	public void setEventName(String eventName) {
		thing1.put("value", eventName);
	}
	public void setMatchTime(String matchTime) {
		thing2.put("value", matchTime);
	}
	public void setMatchType(String matchType) {
		thing3.put("value", matchType);
	}
	public void setRemark(String remark) {
		thing5.put("value", remark);
	}
	public Map<String, String> getThing1() {
		return thing1;
	}
	public Map<String, String> getThing2() {
		return thing2;
	}
	public Map<String, String> getThing3() {
		return thing3;
	}
	public Map<String, String> getThing5() {
		return thing5;
	}
}
