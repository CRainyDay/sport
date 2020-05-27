package com.crainyday.sport.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 管理员查看的裁判信息
 * @author crainyday
 *
 */
public class RefereeAdmin {
	private Integer refereeId;
	private String identity;
	private String refereeName;
	private String refereePhone;
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		Matcher matcher = Pattern.compile("[^0-9]").matcher(identity);
		this.identity = matcher.replaceAll("").trim();
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
