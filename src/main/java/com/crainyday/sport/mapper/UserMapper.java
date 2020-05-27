package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.School;
import com.crainyday.sport.entity.Admin;
import com.crainyday.sport.entity.User;

public interface UserMapper {
	/**
	 * 用户初次登录, 添加openid
	 */
	public boolean addUserOpenid(@Param("openid")String openid);
	/**
	 * 通过openid获取用户信息
	 */
	public User getUserByOpenid(@Param("openid")String openid);
	/**
	 * 管理员认证
	 */
	public void adminIdentify(Admin admin);
	/**
	 * 获取学校简称
	 */
	public String getPrefix(@Param("adminId")Integer adminId);
	/**
	 * 更新用户信息
	 */
	public void updateUser(User user);
	/**
	 * 获取当前所有的学校信息
	 */
	public List<School> getSchools();
	/**
	 * 添加建议或意见
	 */
	public void addSuggestion(@Param("userId")Integer userId, @Param("suggestion")String suggestion);
	/**
	 * 根据用户的userId获取用户的openid
	 */
	public List<String> getOpenids(@Param("userIds")List<Integer> userIds);
}
