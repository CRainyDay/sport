package com.crainyday.sport.data;
/**
 * 对应数据库的event数据表
 * 运动会项目表的简略信息
 * @author crainyday
 *
 */
public class BriefEvent {
	// 项目ID
	private Integer eventId;
	// 项目名字
	private String eventName;
	// 项目类型: 男(1) or 女(2) or 普通(0)
	private Integer eventType = 0;
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
}
