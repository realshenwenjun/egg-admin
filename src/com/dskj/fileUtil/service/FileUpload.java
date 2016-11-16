package com.dskj.fileUtil.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dskj.base.Base;
import com.dskj.util.HttpUtil;
import com.dskj.util.StringUtil;

/**
 * 默认可以上传所有类型文件
 * 
 * @author Administrator
 * 
 */
@Service
public class FileUpload extends Base {
	private String osName = System.getProperties().getProperty("os.name");

	public String upload(String path, MultipartFile file)
			throws FileUploadException, IOException {
		if (file == null || file.getSize() == 0) {
			return "没有选择文件";
		}
		String fileExtName = file.getOriginalFilename();
		fileExtName = fileExtName.substring(fileExtName.lastIndexOf(".") + 1);
		String filename = StringUtil.generateUUID32() + "." + fileExtName;
		File tempFile = null;
		if (osName.toLowerCase().contains("window")) {
			tempFile = new File(System.getProperties().getProperty(
					"java.io.tmpdir")
					+ filename);
		} else {
			tempFile = new File("/usr/local/tomcat/temp/" + filename);
		}
		if (!tempFile.exists())
			tempFile.mkdirs();
		file.transferTo(tempFile);
		//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", tempFile.getAbsolutePath());
		map.put("parentFile", path);
		String result = HttpUtil.post(server + "/oss/object/add", "oss="
				+ objToString(map));
		logger.info(result);

		return readTree(result, "result");
	}

	public static void main(String[] args) {
		System.out
				.println(System.getProperties().getProperty("java.io.tmpdir"));
	}

}
