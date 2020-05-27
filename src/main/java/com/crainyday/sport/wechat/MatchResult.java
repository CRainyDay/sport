package com.crainyday.sport.wechat;

import java.util.HashMap;
import java.util.Map;

/**
 * 比赛结果通知
 * @author crainyday
 *
 */
public class MatchResult {
	private Map<String, String> thing1;
	private Map<String, String> number2;
	private Map<String, String> number3;
	private Map<String, String> thing4;
	private Map<String, String> time5;
	public MatchResult() {
		thing1 = new HashMap<String, String>();
		number2 = new HashMap<String, String>();
		number3 = new HashMap<String, String>();
		thing4 = new HashMap<String, String>();
		time5 = new HashMap<String, String>();
	}
	public void setEventName(String eventName) {
		thing1.put("value", eventName);
	}
	public void setMatchNum(Integer matchNum) {
		number2.put("value", matchNum.toString());
	}
	public void setMatchRanking(Integer ranking) {
		number3.put("value", ranking.toString());
	}
	public void setMatchResult(String matchResult) {
		thing4.put("value", matchResult);
	}
	public void setGradeTime(String time) {
		time5.put("value", time);
	}
	public Map<String, String> getThing1() {
		return thing1;
	}
	public Map<String, String> getNumber2() {
		return number2;
	}
	public Map<String, String> getNumber3() {
		return number3;
	}
	public Map<String, String> getThing4() {
		return thing4;
	}
	public Map<String, String> getTime5() {
		return time5;
	}
}
