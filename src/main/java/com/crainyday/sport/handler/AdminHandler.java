package com.crainyday.sport.handler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crainyday.sport.data.BriefGames;
import com.crainyday.sport.data.EventAdmin;
import com.crainyday.sport.data.MatchReferee;
import com.crainyday.sport.data.RefereeAdmin;
import com.crainyday.sport.entity.Admin;
import com.crainyday.sport.entity.Event;
import com.crainyday.sport.entity.Games;
import com.crainyday.sport.entity.Match;
import com.crainyday.sport.entity.User;
import com.crainyday.sport.service.AdminService;
import com.crainyday.sport.service.EmailService;
import com.crainyday.sport.utils.CodeUtil;
/**
 * /sendCode:				认证管理员前, 发送认证验证码到教育邮箱
 * /adminIdentify:			管理员认证
 * /uploadGeneralExcel:		管理员上传普通用户信息
 * /resetIdentity:			重置用户认证信息
 * 
 * /addGames:				添加运动会信息
 * /updateGames:			修改运动会信息
 * /delGames:				删除运动会信息
 * 
 * /uploadRefereeExcel:		管理员为某个运动会上传裁判信息
 * /pullReferee:			拉取某个运动会的裁判信息
 * /getRefereesByGamesId:	获取运动会现有的裁判信息
 * /delReferee:				删除裁判信息
 * 
 * /uploadEventExcel:		管理员为某个运动会上传项目信息
 * /pullEvent:				拉取某个运动会的项目信息
 * /getEventAdmin:			管理员获取运动会的简略项目信息.
 * /delEvent:				删除项目信息
 * /updateEvent:			修改项目信息
 * 
 * /getBriefGamesByAdminId:	获取管理员管理的运动会简略信息, 用于拉取相关信息
 * /createMatches:			生成单人项目比赛信息
 * /createTeamMatches:		生成团队项目比赛信息
 * 
 * /getMatchesByEventId:	获取某项目的所有比赛信息
 * /updateMatchTime:		更新比赛时间
 * 
 * /getCheckQRCode:			获取检录某一项目比赛的二维码
 * 
 */

@ResponseBody
@Controller
public class AdminHandler {
	@Autowired
	private AdminService adminService;
	@Autowired
	private EmailService emailService;
	/**
	 * 认证管理员前, 发送认证验证码到教育邮箱
	 */
	@PostMapping("/sendCode")
	public Map<String, Object> sendCode(@RequestParam("eduEmail")String toEmail,
								@RequestAttribute("user")User user) throws Exception  {
		String code = CodeUtil.getCodeNum();
		String nickname = user.getNickName();
		// 发送邮件
		emailService.sendMailSimple(toEmail, nickname, code);
		Map<String, Object> map = new HashMap<String, Object>();
		long expires = System.currentTimeMillis() + 600000;
		map.put("code", code);
		map.put("expires", expires);
		map.put("errmsg", "发送成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 管理员认证
	 */
	@PostMapping("/adminIdentify")
	public Map<String, Object> adminIdentify(Admin admin,
								@RequestAttribute("user")User user){
		admin.setAdminId(user.getUserId());
		// 认证管理员信息
		adminService.adminIdentify(admin);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "认证成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 管理员上传普通用户信息
	 */
	@PostMapping("/uploadGeneralExcel")
	public Map<String, Object> uploadGeneralExcel(@RequestParam("file")MultipartFile file,
							@RequestAttribute("user")User user,
							HttpSession session) throws IOException  {
		String realPath = session.getServletContext().getRealPath("WEB-INF/uploads/");
		String filename = file.getOriginalFilename();
		File targetFile = new File(realPath+filename);
		// 暂时保存Excel表格
		file.transferTo(targetFile);
		// 处理Excel表格并存入数据库,
		// 这里上传用户信息的管理员的userId即为用户的adminId
		adminService.addGeneralByExcel(realPath+filename, user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "上传成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 重置用户认证信息
	 */
	@PostMapping("resetIdentity")
	public Map<String, Object> resetIdentity(
			@RequestParam("identity")String identity,
			@RequestAttribute("user")User user) throws Exception{
		adminService.resetIdentity(identity, user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "重置成功");
		map.put("status", 200);
		return map;
	}
	
	
	/**
	 * 添加运动会信息
	 */
	@PostMapping("/addGames")
	public Map<String, Object> addGames(Games games,
									@RequestAttribute("user")User user){
		games.setAdminId(user.getUserId());
		adminService.addGames(games);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "添加成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 修改运动会信息
	 */
	@PostMapping("/updateGames")
	public Map<String, Object> updateGames(Games games,
				@RequestAttribute("user")User user){
		games.setAdminId(user.getUserId());
		adminService.updateGames(games);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "修改成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 删除运动会信息
	 */
	@PostMapping("/delGames")
	public Map<String, Object> delGames(@RequestParam("gamesId")Integer gamesId,
				@RequestAttribute("user")User user) throws Exception{
		adminService.delGames(gamesId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "删除成功");
		map.put("status", 200);
		return map;
	}
	
	
	
	/**
	 * 管理员为某个运动会上传裁判信息
	 */
	@PostMapping("/uploadRefereeExcel")
	public Map<String, Object> uploadRefereeExcel(@RequestParam("file")MultipartFile file,
							@RequestParam("gamesId")Integer gamesId,
							@RequestAttribute("user")User user,
							HttpSession session) throws IOException  {
		String realPath = session.getServletContext().getRealPath("WEB-INF/uploads/");
		String filename = file.getOriginalFilename();
		File targetFile = new File(realPath+filename);
		// 暂时保存Excel表格
		file.transferTo(targetFile);
		// 处理Excel表格并存入数据库, 注意: 这里需要 gamesId 而不是 userId
		adminService.addRefereeByExcel(realPath+filename, gamesId, user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "上传成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 拉取某个运动会的裁判信息
	 */
	@PostMapping("/pullReferee")
	public Map<String, Object> pullReferee(
			@RequestParam("fromGamesId")Integer fromGamesId,
			@RequestParam("toGamesId")Integer toGamesId) throws Exception{
		adminService.pullReferee(fromGamesId, toGamesId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "拉取成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 获取运动会现有的裁判信息
	 */
	@PostMapping("/getRefereesByGamesId")
	public List<RefereeAdmin> getRefereesByGamesId(
			@RequestParam("gamesId")Integer gamesId,
			@RequestParam("page")Integer page, 
			@RequestParam("limit")Integer limit) {
		return adminService.getRefereesByGamesId(gamesId, page, limit);
	}
	
	/**
	 * 删除裁判信息
	 */
	@PostMapping("/delReferee")
	public Map<String, Object> delReferee(@RequestParam("refereeId")Integer refereeId) {
		adminService.delReferee(refereeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "删除成功");
		map.put("status", 200);
		return map;
	}
	
	
	
	/**
	 * 管理员为某个运动会上传项目信息
	 */
	@PostMapping("/uploadEventExcel")
	public Map<String, Object> uploadEventExcel(@RequestParam("file")MultipartFile file,
							@RequestParam("gamesId")Integer gamesId,
							HttpSession session) throws IOException  {
		String realPath = session.getServletContext().getRealPath("WEB-INF/uploads/");
		String filename = file.getOriginalFilename();
		File targetFile = new File(realPath+filename);
		// 暂时保存Excel表格
		file.transferTo(targetFile);
		// 处理Excel表格并存入数据库, 注意: 这里需要 gamesId 而不是 userId
		adminService.addEventByExcel(realPath+filename, gamesId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "上传成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 拉取某个运动会的项目信息
	 */
	@PostMapping("/pullEvent")
	public Map<String, Object> pullEvent(
			@RequestParam("fromGamesId")Integer fromGamesId,
			@RequestParam("toGamesId")Integer toGamesId) throws Exception{
		adminService.pullEvent(fromGamesId, toGamesId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "拉取成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 管理员获取运动会的简略项目信息.
	 */
	@PostMapping("/getEventAdmin")
	public List<EventAdmin> getEventAdmin(@RequestParam("gamesId")Integer gamesId,
			@RequestParam("page")Integer page,
			@RequestParam("limit")Integer limit){
		return adminService.getEventAdmin(gamesId, page, limit);
	}
	
	/**
	 * 修改项目信息
	 */
	@PostMapping("/updateEvent")
	public Map<String, Object> updateEvent(Event event,
				@RequestAttribute("user")User user){
		adminService.updateEvent(event);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "修改成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 删除项目信息
	 */
	@PostMapping("/delEvent")
	public Map<String, Object> delEvent(@RequestParam("eventId")Integer eventId,
				@RequestAttribute("user")User user) throws Exception{
		adminService.delEvent(eventId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "删除成功");
		map.put("status", 200);
		return map;
	}
	
	
	
	/**
	 * 获取管理员管理的运动会简略信息.
	 */
	@PostMapping("/getBriefGamesByAdminId")
	public List<BriefGames> getBriefGamesByAdminId(@RequestAttribute("user")User user){
		return adminService.getBriefGamesByAdminId(user.getUserId());
	}
	
	/**
	 * 生成单人项目比赛信息
	 */
	@PostMapping("/createMatches")
	public Map<String, Object> createMatches(
			@RequestParam("matchType")String matchType,
			Event event)throws Exception{
		adminService.createMatches(event, matchType);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "生成成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 生成团队项目比赛信息
	 */
	@PostMapping("/createTeamMatches")
	public Map<String, Object> createTeamMatches(
			@RequestParam("matchType")String matchType,
			Event event)throws Exception{
		adminService.createTeamMatches(event, matchType);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "生成成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 获取某项目的所有比赛信息
	 */
	@PostMapping("/getMatchesByEventId")
	public List<MatchReferee> getMatchesByEventId(
			@RequestParam("eventId")Integer eventId,
			@RequestParam("page")Integer page,
			@RequestParam("limit")Integer limit){
		return adminService.getMatchesByEventId(eventId, page, limit);
	}
	
	/**
	 * 更新比赛时间
	 */
	@PostMapping("/updateMatchTime")
	public Map<String, Object> updateMatchTime(Match match, 
			@RequestParam("newMatchTime")String newMatchTime,
			@RequestParam("eventName")String eventName) throws Exception  {
		adminService.updateMatchTime(match, newMatchTime, eventName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errmsg", "操作成功");
		map.put("status", 200);
		return map;
	}
	
	/**
	 * 获取检录某一项目比赛的二维码
	 * @return	返回二维码的 URL 链接
	 */
	@PostMapping("/getCheckQRCode")
	public Map<String, Object> getCheckQRCode(HttpSession session,
			@RequestParam("eventId")Integer eventId) throws Exception  {
		String filePath = session.getServletContext().getRealPath("qrcode/");
		String filename = adminService.getCheckQRCode(filePath, eventId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filename", filename);
		map.put("errmsg", "检录二维码生成成功，注意保存！");
		map.put("status", 200);
		return map;
	}
}
