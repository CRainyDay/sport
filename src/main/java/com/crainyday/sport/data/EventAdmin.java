package com.crainyday.sport.data;
/**
 * 对应数据库的event数据表
 * 管理员查看的项目简略信息
 * @author crainyday
 *
 */
public class EventAdmin {
	// 项目ID
	private Integer eventId;
	// 项目名字
	private String eventName;
	// 项目类型: 男(1) or 女(2) or 普通(0), 
	private Integer eventType = 0;
	// 报名人数
	private Integer eventApply;
	// 本轮次参赛人数
	private Integer eventMatch;
	// 比赛轮次
	private Integer eventTurns;
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Integer getEventType() {
		if(eventName!=null&&eventName.contains("男")) {
			eventType = 1;
		}else if(eventName!=null&&eventName.contains("女")) {
			eventType = 2;
		}
		return eventType;
	}
	public Integer getEventApply() {
		return eventApply;
	}
	public void setEventApply(Integer eventApply) {
		this.eventApply = eventApply;
	}
	public Integer getEventTurns() {
		return eventTurns;
	}
	public void setEventTurns(Integer eventTurns) {
		this.eventTurns = eventTurns;
	}
	public Integer getEventMatch() {
		return eventMatch;
	}
	public void setEventMatch(Integer eventMatch) {
		this.eventMatch = eventMatch;
	}
}
