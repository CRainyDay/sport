package com.crainyday.sport.wechat;
/**
 * 报名成功通知
 * 取消报名通知
 * @author crainyday
 *
 */

import java.util.HashMap;
import java.util.Map;

public class ApplyMessage {
	private Map<String, String> thing1;
	private Map<String, String> thing2;
	private Map<String, String> time3;
	private Map<String, String> time4;
	private Map<String, String> thing5;
	public ApplyMessage() {
		thing1 = new HashMap<String, String>();
		thing2 = new HashMap<String, String>();
		time3 = new HashMap<String, String>();
		time4 = new HashMap<String, String>();
		thing5 = new HashMap<String, String>();
	}
	public void setGamesName(String gamesName) {
		thing1.put("value", gamesName);
	}
	public void setEventName(String eventName) {
		thing2.put("value", eventName);
	}
	public void setApplyDate(String applyDate) {
		time3.put("value", applyDate);
	}
	public void setApplyEnd(String applyEnd) {
		time4.put("value", applyEnd);
	}
	public void setTip(String tip) {
		thing5.put("value", tip);
	}
	public Map<String, String> getThing1() {
		return thing1;
	}
	public Map<String, String> getThing2() {
		return thing2;
	}
	public Map<String, String> getTime3() {
		return time3;
	}
	public Map<String, String> getTime4() {
		return time4;
	}
	public Map<String, String> getThing5() {
		return thing5;
	}
}
