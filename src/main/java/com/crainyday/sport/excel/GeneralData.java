package com.crainyday.sport.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 上传的普通用户Excel信息对应的对象
 * @author crainyday
 *
 */
public class GeneralData {
	@ExcelProperty(index = 0)
	private String identity;
	@ExcelProperty(index = 1)
	private String generalClass;
	@ExcelProperty(index = 2)
	private String generalName;
	@ExcelProperty(index = 3)
	private String gender;
	private Integer generalGender = 0;
	@ExcelProperty(index = 4)
	private String generalCollege;
	@ExcelProperty(index = 5)
	private String generalPhone;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getGeneralClass() {
		return generalClass;
	}
	public void setGeneralClass(String generalClass) {
		this.generalClass = generalClass;
	}
	public String getGeneralName() {
		return generalName;
	}
	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
		if(gender!=null&&gender.contains("男")) {
			this.generalGender = 1;
		}else if(gender!=null&&gender.contains("女")) {
			this.generalGender = 2;
		}
	}
	public Integer getGeneralGender() {
		return generalGender;
	}
	public void setGeneralGender(Integer generalGender) {
		this.generalGender = generalGender;
	}
	public String getGeneralCollege() {
		return generalCollege;
	}
	public void setGeneralCollege(String generalCollege) {
		this.generalCollege = generalCollege;
	}
	public String getGeneralPhone() {
		return generalPhone;
	}
	public void setGeneralPhone(String generalPhone) {
		this.generalPhone = generalPhone;
	}
}
