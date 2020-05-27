package com.crainyday.sport.handler;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crainyday.sport.exception.ApplyException;
import com.crainyday.sport.exception.EventException;
import com.crainyday.sport.exception.GamesException;
import com.crainyday.sport.exception.GeneralException;
import com.crainyday.sport.exception.LoginException;

@RestControllerAdvice
public class WebExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);
	@ExceptionHandler(Exception.class)
    Map<String, Object> handleException(Exception e){
        LOGGER.error(e.getMessage(), e);
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "未知异常，请重试！");
		map.put("status", 403);
		return map;
    }
	@ExceptionHandler(LoginException.class)
    Map<String, Object> handleLoginException(LoginException e){
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", e.getMessage());
		map.put("status", 403);
		return map;
    }
	@ExceptionHandler(MessagingException.class)
    Map<String, Object> handleEmailException(MessagingException e){
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", e.getMessage());
		map.put("status", 403);
		return map;
    }
	@ExceptionHandler(ApplyException.class)
    Map<String, Object> handleApplyException(ApplyException e){
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", e.getMessage());
		map.put("status", 403);
		return map;
    }
	@ExceptionHandler(GamesException.class)
    Map<String, Object> handleGamesException(GamesException e){
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", e.getMessage());
		map.put("status", 403);
		return map;
    }
	@ExceptionHandler(EventException.class)
    Map<String, Object> handleEventException(EventException e){
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", e.getMessage());
		map.put("status", 403);
		return map;
    }
	@ExceptionHandler(GeneralException.class)
    Map<String, Object> handleGeneralException(GeneralException e){
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", e.getMessage());
		map.put("status", 403);
		return map;
    }
}
