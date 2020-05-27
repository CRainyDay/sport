package com.crainyday.sport;

import java.util.regex.Pattern;

import org.junit.Test;

public class TestPattern {
	@Test
	public void testEmail() {
		String email = "xxxxxxxxxxxx@mail.ujn.edu.cn";
		Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)?(\\.edu\\.cn)$");
		if(!emailPattern.matcher(email).matches()) {
			System.out.println("请输入教育邮箱!!!");
		}else {
			System.out.println("邮箱正确!!!");
		}
	}
}
