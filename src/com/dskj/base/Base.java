package com.dskj.base;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;

import com.dskj.response.JsonResponse;

public abstract class Base {
	public Logger logger;
	public static ObjectMapper objectMapper;
	public JsonNode rootNode = null;
	public static String server = "Http://101.201.212.96/dskj";

	public static String defaultPage = "index";

	public Base() {
		if (logger == null) {
			logger = Logger.getLogger(this.getClass());
		}
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
			objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
			objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
	}

	/**
	 * Object转成String
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static String objToString(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		// 将结果转化为JSON字符串
		String jsonString = objectMapper.writeValueAsString(object);
		Pattern p = Pattern.compile("\t|\r|\n");
		Matcher m = p.matcher(jsonString);
		jsonString = m.replaceAll("");
		return jsonString;
	}

	/**
	 * String转Object
	 * 
	 * @param json
	 * @param T
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T stringToObj(String json, Class<?> T) throws JsonParseException, JsonMappingException, IOException {
		T t = (T) objectMapper.readValue(json, T);
		return t;
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public static <T> List<T> jsonToList(String json, Class<?> collectionClass, Class<?>... elementClasses) {
		if (json == null)
			return null;
		JavaType javaType = getCollectionType(collectionClass, elementClasses);
		List<T> list = null;
		try {
			list = objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(Base.class).error(e.toString());
		}
		return list;
	}
	
	public void write(HttpServletResponse response, Boolean success, Integer code, String message, Object obj) {
		JsonResponse jsonResponse = new JsonResponse();
		if (success != null && !success)
			jsonResponse.setSuccess(success);
		if (code != null && code != 000)
			jsonResponse.setCode(code);
		if (message != null && !"".equals(message) && !"成功".equals(message))
			jsonResponse.setMessage(message);
		jsonResponse.setResult(obj);
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("UTF-8");
			logger.info(objToString(jsonResponse));
			response.getWriter().write(objToString(jsonResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String readTree(String json, String key) throws JsonProcessingException, IOException {
		rootNode = objectMapper.readTree(json);
		String cJson = rootNode.path(key).toString();
		if(cJson.startsWith("\"") && cJson.endsWith("\""))
			cJson = cJson.substring(1, cJson.length()-1);
		return cJson;
	}
	public Integer readTreeAsInt(String json, String key) throws JsonProcessingException, IOException {
		rootNode = objectMapper.readTree(json);
		Integer cJson = rootNode.path(key).asInt();
		return cJson;
	}
	public long readTreeAsLong(String json, String key) throws JsonProcessingException, IOException {
		rootNode = objectMapper.readTree(json);
		long cJson = rootNode.path(key).asLong();
		return cJson;
	}
}
