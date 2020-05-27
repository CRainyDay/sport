package com.crainyday.sport.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 上传的运动会项目Excel信息对应的对象
 * @author crainyday
 *
 */
public class EventData {
	@ExcelProperty(index = 0)
	private String eventName;
	@ExcelProperty(index = 1)
	private String eventDesc;
	@ExcelProperty(index = 2)
	private Integer eventNum;
	@ExcelProperty(index = 3)
	private String eventRule;
	@ExcelProperty(index = 4)
	private String recordGrade;
	@ExcelProperty(index = 5)
	private String recordName;
	@ExcelProperty(index = 6)
	private String recordCollege;
	@ExcelProperty(index = 7)
	private String recordGames;
	@ExcelProperty(index = 8)
	private String recordTp;
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
	public Integer getEventNum() {
		return eventNum;
	}
	public void setEventNum(Integer eventNum) {
		this.eventNum = eventNum;
	}
	public String getEventRule() {
		return eventRule;
	}
	public void setEventRule(String eventRule) {
		this.eventRule = eventRule;
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
