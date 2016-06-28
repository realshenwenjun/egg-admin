package com.dskj.sms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dskj.base.Base;
import com.dskj.util.HttpUtil;

/**
 * Created by shenwenjun on 2016/4/28.
 */
@Controller
public class SMSController extends Base {
	
	public static Map<String, Object> codePool = new HashMap<String, Object>();
	
	/**
	 * 发送短信
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sms/send.do", method = RequestMethod.POST)
	public void sendSMS(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "phone") String phone) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("templeteId", "sms_regist_templateId");
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/user/check/phone",
					"user=" + objToString(map));
			if(readTreeAsInt(result, "code") == 906){
				write(response, false, 911, "手机号未注册", null);
				return;
			}
			if(readTreeAsInt(result, "code") != 0){
				write(response, false, 911, "验证手机号异常", null);
				return;
			}
			result = HttpUtil.post(server + "/sms/send",
					"sms=" + objToString(map));
			int code = readTreeAsInt(result, "code");
			if (code == 0) {
				Map<String, Object> codeMap = stringToObj(
						readTree(result, "result"), HashMap.class);
				codePool.put(phone, codeMap.get("code"));
				write(response, null, null, null, codeMap.get("code"));
			} else {
				write(response, false, 911, "获取验证码失败", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
