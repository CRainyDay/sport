package com.crainyday.sport.aipface;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

//@Component
public class Face {
    //设置APPID/AK/SK
    public static final String APP_ID = "19914135";
    public static final String API_KEY = "1VdOhrtOtCjMp5dPTZWDNdiF";
    public static final String SECRET_KEY = "Vuh0GFS9QR5MFnTWMxo7I9b3lyizk3EQ";
    public static AipFace aipFace;
    public Face() {
		this.init();
	}
    public void init() {
    	aipFace = new AipFace(APP_ID, API_KEY, SECRET_KEY);
    }
    /**
     * 获取 AipFace 的客户端
     * @return
     */
	public AipFace getAipFace() {
		return aipFace;
	}
	/**
	 * 人脸注册
	 */
	public void faceRegister() {
	    // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("user_info", "user's info");
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
	    options.put("action_type", "REPLACE");
	    
	    String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
	    String imageType = "BASE64";
	    String groupId = "sportsman";
	    String userId = "general_id_1";
	    
	    // 人脸注册
	    JSONObject res = aipFace.addUser(image, imageType, groupId, userId, options);
	    System.out.println(res.toString(2));
	}
	/**
	 * 人脸搜索
	 */
	public void faceSearch() {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("max_face_num", "3");
	    options.put("match_threshold", "70");
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
	    options.put("user_id", "general_id_1");
	    options.put("max_user_num", "3");
	    String image = "BASE64 Data";
	    String imageType = "BASE64";
	    
	    String groupIdList = "sportsman";
	    
	    // 人脸搜索
	    JSONObject res = aipFace.search(image, imageType, groupIdList, options);
	    System.out.println(res.toString(2));
	}
}
