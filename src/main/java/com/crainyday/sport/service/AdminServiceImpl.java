package com.crainyday.sport.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.crainyday.sport.data.BriefGames;
import com.crainyday.sport.data.EventAdmin;
import com.crainyday.sport.data.MatchReferee;
import com.crainyday.sport.data.RefereeAdmin;
import com.crainyday.sport.entity.Admin;
import com.crainyday.sport.entity.Apply;
import com.crainyday.sport.entity.Event;
import com.crainyday.sport.entity.Games;
import com.crainyday.sport.entity.Match;
import com.crainyday.sport.entity.Referee;
import com.crainyday.sport.entity.Team;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.excel.EventData;
import com.crainyday.sport.excel.GeneralData;
import com.crainyday.sport.excel.ReadEventListener;
import com.crainyday.sport.excel.ReadGeneralListener;
import com.crainyday.sport.excel.ReadRefereeListener;
import com.crainyday.sport.excel.RefereeData;
import com.crainyday.sport.exception.EventException;
import com.crainyday.sport.exception.GamesException;
import com.crainyday.sport.mapper.ApplyMapper;
import com.crainyday.sport.mapper.EventMapper;
import com.crainyday.sport.mapper.GamesMapper;
import com.crainyday.sport.mapper.GeneralMapper;
import com.crainyday.sport.mapper.GradeMapper;
import com.crainyday.sport.mapper.MatchMapper;
import com.crainyday.sport.mapper.RefereeMapper;
import com.crainyday.sport.mapper.TeamMapper;
import com.crainyday.sport.mapper.UserMapper;
import com.crainyday.sport.utils.JsonUtil;
import com.crainyday.sport.utils.WeChatUtil;
import com.crainyday.sport.wechat.MatchBegin;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	// 默认每场比赛最大人数
	private static final Integer DEFALUT_MATCH_NUM = 8;
	@Autowired
	private GeneralMapper generalMapper;
	@Autowired
	private RefereeMapper refereeMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GamesMapper gamesMapper;
	@Autowired
	private EventMapper eventMapper;
	@Autowired
	private ApplyMapper applyMapper;
	@Autowired
	private MatchMapper matchMapper;
	@Autowired
	private GradeMapper gradeMapper;
	@Autowired
	private TeamMapper teamMapper;
	@Autowired
	private WeChatUtil weChatUtil;
	/**
	 * 管理员认证
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void adminIdentify(Admin admin) {
		userMapper.adminIdentify(admin);
		// 更新用户类别
		User user = new User();
		user.setUserId(admin.getAdminId());
		user.setUserType(-1);
		user.setAdminId(admin.getAdminId());
		userMapper.updateUser(user);
	}
	/**
	 * 管理员批量上传普通用户信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void addGeneralByExcel(String filepath, Integer adminId) {
		ExcelReader excelReader = null;
		try {
			String prefix = userMapper.getPrefix(adminId);
			excelReader = EasyExcel.read(filepath, GeneralData.class, new ReadGeneralListener(generalMapper, adminId, prefix)).build();
	        ReadSheet readSheet = EasyExcel.readSheet(0).build();
	        excelReader.read(readSheet);
		} finally {
			if(excelReader != null) {
				// 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
				excelReader.finish();
			}
		}
		// 插入数据库成功, 删除Excel文件
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
		}
	}
	/**
	 * 管理员批量上传裁判信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void addRefereeByExcel(String filepath, Integer gamesId, Integer adminId) {
		ExcelReader excelReader = null;
		try {
			String prefix = userMapper.getPrefix(adminId);
			excelReader = EasyExcel.read(filepath, RefereeData.class, new ReadRefereeListener(refereeMapper, gamesId, prefix)).build();
	        ReadSheet readSheet = EasyExcel.readSheet(0).build();
	        excelReader.read(readSheet);
		} finally {
			if(excelReader != null) {
				// 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
				excelReader.finish();
			}
		}
		// 插入数据库成功, 删除Excel文件
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
		}
	}
	/**
	 * 拉取某个运动会的裁判信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void pullReferee(Integer fromGamesId, Integer toGamesId) throws Exception {
		if(refereeMapper.judgePull(toGamesId)) {
			throw new GamesException("已经拉取过裁判信息");
		}
		List<Referee> referees = refereeMapper.getRefereeList(fromGamesId);
		if(referees.size() == 0) {
			throw new GamesException("源运动会裁判信息为空");
		}
		refereeMapper.addRefereeByList(referees, toGamesId);
	}
	/**
	 * 添加运动会信息
	 */
	public void addGames(Games games) {
		gamesMapper.addGames(games);
	}
	/**
	 * 修改运动会信息
	 */
	public void updateGames(Games games) {
		gamesMapper.updateGames(games);
	}
	/**
	 * 删除运动会信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void delGames(Integer gamesId) throws Exception {
		try {
			gamesMapper.delGames(gamesId);
		} catch (Exception e) {
			throw new GamesException("运动会有项目或裁判信息, 清理后方可删除");
		}
	}
	/**
	 * 查询运动会信息
	 */
	public Games getGamesByGamesId(Integer gamesId) {
		return gamesMapper.getGamesByGamesId(gamesId);
	}
	/**
	 * 管理员批量上传运动会的项目信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void addEventByExcel(String filepath, Integer gamesId) {
		ExcelReader excelReader = null;
		try {
			excelReader = EasyExcel.read(filepath, EventData.class, new ReadEventListener(eventMapper, gamesId)).build();
	        ReadSheet readSheet = EasyExcel.readSheet(0).build();
	        excelReader.read(readSheet);
		} finally {
			if(excelReader != null) {
				// 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
				excelReader.finish();
			}
		}
		// 插入数据库成功, 删除Excel文件
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
		}
	}
	/**
	 * 拉取某个运动会的项目信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void pullEvent(Integer fromGamesId, Integer toGamesId) throws Exception {
		if(eventMapper.judgePull(toGamesId)) {
			throw new GamesException("已经拉取过项目信息");
		}
		List<Event> events = eventMapper.getEventList(fromGamesId);
		if(events.size() == 0) {
			throw new GamesException("源运动会项目信息为空");
		}
		eventMapper.addEventByList(events, toGamesId);
	}
	/**
	 * 修改项目信息
	 */
	public void updateEvent(Event event) {
		eventMapper.updateEvent(event);
	}
	/**
	 * 删除项目信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void delEvent(Integer eventId) throws Exception {
		try {
			eventMapper.delEvent(eventId);
		} catch (Exception e) {
			throw new EventException("无法删除，项目存在比赛或报名信息！");
		}
	}
	/**
	 * 获取项目信息
	 */
	public Event getEventByEventId(Integer eventId) {
		return eventMapper.getEventByEventId(eventId);
	}
	/**
	 * 获取管理员所管理的所有运动会的简略信息
	 */
	public List<BriefGames> getBriefGamesByAdminId(Integer adminId) {
		return gamesMapper.getBriefGamesByAdminId(adminId);
	}
	
	/**
	 * 生成单人项目比赛信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void createMatches(Event event, String matchType) throws Exception {
		if(event.getEventApply()!=0&&matchMapper.judgeCreatMatches(event.getEventId())) {
			throw new EventException("上一阶段比赛尚未完成");
		}
		List<Apply> allUsers = null;
		if(event.getEventApply()!=0) {
			// 由之前的比赛成绩生成比赛信息
			// 获取该项目之前的比赛信息, 找到成绩最好的 eventMatch 个人参加比赛
			List<Integer> matchIds = matchMapper.getMatchIdsByEventId(event.getEventId());
			List<Integer> bestUserIds = gradeMapper.getBestUserIds(matchIds, event.getEventMatch());
			bestUserIds.add(0);
			allUsers = applyMapper.getApplysByUserIds(bestUserIds, event.getEventId());
		}else {
			// 由报名信息首次生成比赛信息
			allUsers = applyMapper.getApplysByEventId(event.getEventId());
		}
		
		List<Integer> referees = refereeMapper.getRefereeIdsByGamesId(event.getGamesId());
		Random random = new Random();
		int matchNum = (int) Math.ceil(event.getEventMatch()*1.0 / AdminServiceImpl.DEFALUT_MATCH_NUM);
		for (int i = 0; i < matchNum; i++) {
			if(referees.size() == 0) {
				referees = refereeMapper.getRefereeIdsByGamesId(event.getGamesId());
			}
			List<Integer> userIds = new ArrayList<Integer>();
			List<Apply> applys = this.getRandomUserId(allUsers, userIds);
			// 创建比赛
			Match match = new Match();
			match.setEventId(event.getEventId());
			match.setMatchGroup(i + 1);
			match.setMatchNum(applys.size());
			match.setMatchType(matchType);
			// 为比赛设置裁判信息
			int index = referees.size()>1?random.nextInt(referees.size() - 1):0;
			match.setRefereeId(referees.get(index));
			referees.remove(index);
			String userIdsJson = "[]";
			// 添加比赛的用户IDs
			userIdsJson = JsonUtil.List2Json(userIds);
			match.setUserIds(userIdsJson);
			// 添加比赛
			matchMapper.addMatch(match);
			// 更新报名当前项目的用户报名信息(比赛信息)
			this.addMatchId(applys, match.getMatchId());
			// 为用户添加跑道等信息
			this.addRunWay(userIds, match.getMatchId());
		}
		// 一个项目的比赛信息生成完毕, 更新项目的比赛轮次和本轮比赛人数
		eventMapper.updateEventTurns(event.getEventId(), 1, event.getEventMatch());
	}
	/**
	 * 从项目所有的所有报名者中随机取出 DEFALUT_MATCH_NUM 个人,
	 * 用于同一个比赛
	 */
	private List<Apply> getRandomUserId(List<Apply> allUsers, List<Integer> userIds){
		List<Apply> applyIds = new ArrayList<Apply>();
		Random random = new Random();
		int userSum = allUsers.size();
		int userNum = userSum <= AdminServiceImpl.DEFALUT_MATCH_NUM?userSum:AdminServiceImpl.DEFALUT_MATCH_NUM;
		// 保证每场比赛人数在 4~8 人之间
		if(userSum>8&&userSum<13) {
			userNum = userSum / 2;
		}
		for (int i = 0; i < userNum; i++) {
			int index = allUsers.size()>1?random.nextInt(allUsers.size() - 1):0;
			applyIds.add(allUsers.get(index));
			userIds.add(allUsers.get(index).getUserId());
			allUsers.remove(index);
		}
		return applyIds;
	}
	private void addRunWay(List<Integer> userIds, Integer matchId){
		int number = 1;
		StringBuffer runway = null;
		for (Integer userId : userIds) {
			runway = new StringBuffer(number + "号"); number++;
			gradeMapper.addGradeRunway(userId, matchId, runway.toString());
		}
	}
	/**
	 * 更新报名用户的比赛信息
	 */
	private void addMatchId(List<Apply> applys, Integer matchId) {
		try {
			String matchJson = "[]";
			for (Apply apply : applys) {
				String matchIdsString = apply.getMatchIds();
				if(matchIdsString == null||matchIdsString.equals("")) {
					matchIdsString = "[]";
				}
				List<Integer> matchIds = JsonUtil.Json2List(matchIdsString, Integer.class);
				matchIds.add(matchId);
				matchJson = JsonUtil.List2Json(matchIds);
				apply.setMatchIds(matchJson);
				applyMapper.updateMatchIdsByApply(apply);
			}
		} catch (JsonProcessingException e) {
		}
	}
	/**
	 * 生成团体项目比赛信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void createTeamMatches(Event event, String matchType) throws Exception {
		if(event.getEventApply()!=0&&matchMapper.judgeCreatMatches(event.getEventId())) {
			throw new EventException("上一阶段比赛尚未完成");
		}
		List<Team> allTeams = null;
		if(event.getEventApply()!=0) {
			// 由之前的比赛成绩生成比赛信息
			// 获取该项目之前的比赛信息, 找到成绩最好的 eventMatch 个队伍参加比赛
			allTeams = teamMapper.getApplysByScore(event.getEventId(), event.getEventMatch());
		}else {
			// 由报名信息首次生成比赛信息
			allTeams = teamMapper.getApplysByEventId(event.getEventId());
		}
		List<Integer> referees = refereeMapper.getRefereeIdsByGamesId(event.getGamesId());
		Random random = new Random();
		int matchNum = (int) Math.ceil(event.getEventMatch()*1.0 / AdminServiceImpl.DEFALUT_MATCH_NUM);
		for (int i = 0; i < matchNum; i++) {
			if(referees.size() == 0) {
				referees = refereeMapper.getRefereeIdsByGamesId(event.getGamesId());
			}
			List<Integer> userIds = new ArrayList<Integer>();
			List<Team> teams = this.getRandomTeam(allTeams, userIds);
			// 创建比赛
			Match match = new Match();
			match.setEventId(event.getEventId());
			match.setMatchGroup(i + 1);
			match.setMatchNum(teams.size()*4);
			match.setMatchType(matchType);
			// 为比赛设置裁判信息
			int index = referees.size()>1?random.nextInt(referees.size() - 1):0;
			match.setRefereeId(referees.get(index));
			referees.remove(index);
			
			String userIdsJson = "[]";
			// 添加比赛的用户IDs
			userIdsJson = JsonUtil.List2Json(userIds);
			match.setUserIds(userIdsJson);
			// 添加比赛
			matchMapper.addMatch(match);
			// 更新参赛队伍的比赛信息
			updateTeamMatch(teams, match.getMatchId(), event.getEventId());
			List<Apply> applys = applyMapper.getApplysByUserIds(userIds, event.getEventId());
			// 更新参赛队伍队员的报名信息(比赛信息)
			this.addMatchId(applys, match.getMatchId());
		}
		// 一个项目的比赛信息生成完毕, 更新项目的比赛轮次和本轮比赛人数
		eventMapper.updateEventTurns(event.getEventId(), 1, event.getEventMatch());
	}
	private void updateTeamMatch(List<Team> teams, Integer matchId, Integer eventId) throws Exception {
		String matchJson = "[]";
		StringBuffer runway = null;
		List<Integer> ids = null;
		int number = 1;
		for (Team team : teams) {
			String matchIdsString = team.getMatchIds();
			if(matchIdsString == null||matchIdsString.equals("")) {
				matchIdsString = "[]";
			}
			
			String userIdsJson = team.getUserIds();
			ids = JsonUtil.Json2List(userIdsJson, Integer.class);
			// 更新用户报名表的比赛ID信息
			List<Apply> applys = applyMapper.getApplysByUserIds(ids, eventId);
			addMatchId(applys, matchId);
			// 为用户添加跑道等信息
			for (int i = 0; i < ids.size(); i++) {
				runway = new StringBuffer( number + "号；第"+(i+1)+"棒");
				gradeMapper.addGradeRunway(ids.get(i), matchId, runway.toString());
			}
			runway = new StringBuffer( number + "号");
			number++;
			List<Integer> matchIds = JsonUtil.Json2List(matchIdsString, Integer.class);
			matchIds.add(matchId);
			matchJson = JsonUtil.List2Json(matchIds);
			team.setMatchIds(matchJson);
			team.setRunway(runway.toString());
			teamMapper.updateMatchIdsByTeam(team);
		}
	}
	/**
	 * 从项目所有的所有报名者中随机取出 DEFALUT_MATCH_NUM 个人,
	 * 用于同一个比赛
	 */
	private List<Team> getRandomTeam(List<Team> allUsers, List<Integer> userIds) throws Exception{
		List<Team> applyIds = new ArrayList<Team>();
		Random random = new Random();
		int userSum = allUsers.size();
		int userNum = userSum <= AdminServiceImpl.DEFALUT_MATCH_NUM?userSum:AdminServiceImpl.DEFALUT_MATCH_NUM;
		// 保证每场比赛队数在 4~8 队之间
		if(userSum>8&&userSum<13) {
			userNum = userSum / 2;
		}
		for (int i = 0; i < userNum; i++) {
			int index = allUsers.size()>1?random.nextInt(allUsers.size() - 1):0;
			applyIds.add(allUsers.get(index));
			userIds.add(allUsers.get(index).getUserId());
//			String users = allUsers.get(index).getUserIds();
//			userIds.addAll(JsonUtil.Json2List(users, Integer.class));
			allUsers.remove(index);
		}
		return applyIds;
	}
	/**
	 * 获取运动会现有的裁判信息
	 */
	public List<RefereeAdmin> getRefereesByGamesId(Integer gamesId, Integer page, Integer limit) {
		return refereeMapper.getRefereesByGamesId(gamesId, (page-1)*limit, limit);
	}
	/**
	 * 删除指定裁判信息
	 */
	@Transactional(propagation = Propagation.REQUIRED,
			   isolation = Isolation.READ_COMMITTED,
			   readOnly = false)
	public void delReferee(Integer refereeId) {
		refereeMapper.delReferee(refereeId);
	}
	/**
	 * 获取某项目的所有比赛信息
	 */
	public List<MatchReferee> getMatchesByEventId(Integer eventId, Integer page, Integer limit) {
		return matchMapper.getMatchesByEventId(eventId, (page-1)*limit, limit);
	}
	/**
	 * 更新比赛时间
	 */
	public void updateMatchTime(Match match, String newMatchTime, String eventName) throws Exception {
		matchMapper.updateMatchTime(match);
		// 发送小程序订阅消息, 通知用户比赛时间已经更新
		String userIdsJson = matchMapper.getUserIdsByMatchId(match.getMatchId());
		List<Integer> userIds = JsonUtil.Json2List(userIdsJson, Integer.class);
		List<String> openids = userMapper.getOpenids(userIds);
		MatchBegin message = new MatchBegin();
		message.setEventName(eventName);
		message.setMatchTime(newMatchTime);
		message.setMatchType(match.getMatchType());
		message.setRemark("注意比赛时间哦");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("template_id", "y4depWCosMgBGQpRxuWix8X0ei461pO8Ec2rxOprSGg");
		param.put("page", "index/index");
		for (String openid : openids) {
			param.put("touser", openid);
			weChatUtil.sendSubscribeMessage(param, message);
		}
	}
	/**
	 * 管理员获取运动会的简略项目信息.
	 */
	public List<EventAdmin> getEventAdmin(Integer gamesId, Integer page, Integer limit) {
		return eventMapper.getEventAdmin(gamesId, (page-1)*limit, limit);
	}
	/**
	 * 获取检录某一项目比赛的二维码
	 */
	public String getCheckQRCode(String filePath, Integer eventId) throws Exception {
		String fileName = "event_" + eventId + ".png";
		List<Integer> matchIds = matchMapper.getEventNewestMatchIds(eventId);
		if(matchIds.size() == 0) {
			throw new EventException("暂无待检录比赛，请先更新比赛时间！");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		// 过期时间: 30分钟
		long expires = System.currentTimeMillis() / 1000 + 1800;
		// scene参数: expires过期时间 eventId项目ID
		param.put("scene", "a=" + expires + "&b=" + eventId);
		weChatUtil.getWxacodeUnlimited(filePath + fileName, param);
		return fileName;
	}
}
