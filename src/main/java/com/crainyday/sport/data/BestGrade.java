package com.crainyday.sport.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 十佳成绩
 * @author crainyday
 *
 */
public class BestGrade {
	private String identity;
	private String generalClass;
	private String generalName;
	private String gradeResult;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		Matcher matcher = Pattern.compile("[^0-9]").matcher(identity);
		this.identity = matcher.replaceAll("").trim();
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
	public String getGradeResult() {
		return gradeResult;
	}
	public void setGradeResult(String gradeResult) {
		this.gradeResult = gradeResult;
	}
}
