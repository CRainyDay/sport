package com.crainyday.sport.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.crainyday.sport.entity.User;
import com.crainyday.sport.service.LoginService;

/**
 * 令牌拦截器
 * @author crainyday
 *
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
	@Autowired
	private LoginService loginService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("token");
		if(token == null || "".equals(token.trim())) {
//			System.out.println("拦截");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("{\"status\": 403, \"errmsg\": \"令牌非法, 请先登录\"}");
			out.close();
			return false;
		}
		User user = loginService.getUserByOpenid(token);
		if(user == null) {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("{\"status\": 403, \"errmsg\": \"令牌非法, 请先登录\"}");
			out.close();
			return false;
		}
		request.setAttribute("user", user);
		return true;
	}
}
