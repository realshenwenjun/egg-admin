package com.dskj.fileUtil.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dskj.base.Base;
import com.dskj.fileUtil.service.FileUpload;

@Controller
public class FileControll extends Base {
	@Autowired
	private FileUpload fileUpload;

	/*
	 * 上传图片
	 */
	@RequestMapping("/file/upload.do")
	public void uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "fileDir", required = true) String fileDir, HttpServletRequest request, HttpServletResponse response) {
		try {
			String filePath = fileUpload.upload(fileDir,file);
			write(response, null, null, null, filePath);
		} catch (Exception e) {
			e.printStackTrace();
			write(response, false, 911, e.getMessage(), null);
		}
	}
	
	/*
	 * 富文本上传图片
	 */
	@RequestMapping("/image/upload.do")
	public void uploadImageTest(@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "fileDir", required = true) String fileDir, HttpServletRequest request, HttpServletResponse response) {
		try {
			String filePath = fileUpload.upload(fileDir,file);
			response.setCharacterEncoding("UTF-8");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", 0);
			map.put("url", filePath);
			response.getWriter().write(objToString(map));
		} catch (Exception e) {
			e.printStackTrace();
			write(response, false, 911, e.getMessage(), null);
		}
	}

}
