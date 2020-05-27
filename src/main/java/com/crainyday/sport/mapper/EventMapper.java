package com.crainyday.sport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crainyday.sport.data.BriefEvent;
import com.crainyday.sport.data.EventAdmin;
import com.crainyday.sport.entity.Event;
import com.crainyday.sport.excel.EventData;

public interface EventMapper {
	/**
	 * 将解析得到的Excel批量添加裁判信息
	 */
	public boolean addEventByExcel(@Param("datas")List<EventData> datas, @Param("gamesId")Integer gamesId);
	/**
	 * 修改一个项目信息
	 */
	public void updateEvent(Event event);
	/**
	 * 删除一个项目信息
	 */
	public void delEvent(@Param("eventId")Integer eventId);
	/**
	 * 查询一个项目信息
	 */
	public Event getEventByEventId(@Param("eventId")Integer eventId);
	/**
	 * 获取运动会gamesId的所有项目的简略信息
	 */
	public List<BriefEvent> getEventByGamesId(@Param("gamesId")Integer gamesId, @Param("page")Integer page, @Param("limit")Integer limit);
	/**
	 * 判断当前项目报名人数是否已满
	 */
	public boolean judgeEventFull(@Param("eventId")Integer eventId);
	/**
	 * 获取某个运动会所有的项目
	 */
	public List<Event> getEventsByGamesId(@Param("gamesId")Integer gamesId);
	/**
	 * 判断项目是否已经生成了第一阶段的比赛信息
	 */
	public Boolean judgeCreateFirstMatches(@Param("eventId")Integer eventId);
	/**
	 * 获取某个运动会所有的项目ID
	 */
	public List<Integer> getEventIdsByGamesId(@Param("gamesId")Integer gamesId);
	/**
	 * 管理员获取运动会的简略项目信息.
	 */
	public List<EventAdmin> getEventAdmin(@Param("gamesId")Integer gamesId, @Param("page")Integer page, @Param("limit")Integer limit);
	/**
	 * 更新项目的比赛轮次
	 */
	public void updateEventTurns(@Param("eventId")Integer eventId, @Param("eventTurns")Integer eventTurns, @Param("eventMatch")Integer eventMatch);
	/**
	 * 获取运动会所有的项目信息
	 */
	public List<Event> getEventList(@Param("gamesId")Integer gamesId);
	/**
	 * 通过List批量添加项目信息
	 */
	public void addEventByList(@Param("events")List<Event> events, @Param("gamesId")Integer gamesId);
	/**
	 * 判断运动会是否有项目信息, 用于之后拉取运动会的项目信息
	 */
	public Boolean judgePull(@Param("gamesId")Integer gamesId);
}
