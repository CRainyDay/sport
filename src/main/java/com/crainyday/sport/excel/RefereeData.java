package com.crainyday.sport.excel;

import com.alibaba.excel.annotation.ExcelProperty;
/**
 * 上传的裁判Excel信息对应的对象
 * @author crainyday
 *
 */
public class RefereeData {
	@ExcelProperty(index = 0)
	private String identity;
	@ExcelProperty(index = 1)
	private String refereeName;
	@ExcelProperty(index = 2)
	private String refereePhone;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getRefereeName() {
		return refereeName;
	}
	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}
	public String getRefereePhone() {
		return refereePhone;
	}
	public void setRefereePhone(String refereePhone) {
		this.refereePhone = refereePhone;
	}
}
