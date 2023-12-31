package me.wll.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Jackson工具类
 * 
 * @author libing@wisoft.com.cn
 *
 */
public class JacksonUtil {
	
	private final static ObjectMapper objectMapper = new ObjectMapper();

	private JacksonUtil() {

	}

	static {
		// 忽略多余字段
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ObjectMapper getInstance() {
		return objectMapper;
	}

	/**
	 * javaBean、列表数组转换为json字符串
	 */
	public static String obj2json(Object obj) throws RuntimeException {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * javaBean、列表数组转换为json字符串,忽略空值
	 */
	public static String obj2jsonIgnoreNull(Object obj) throws RuntimeException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * json 转JavaBean
	 */
	public static <T> T json2pojo(String jsonString, Class<T> clazz) throws RuntimeException {
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return objectMapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * json字符串转换为map
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonString) throws RuntimeException {
		if (jsonString == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			return mapper.readValue(jsonString, Map.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * json字符串转换为map
	 */
	public static <T> Map<String, T> json2map(String jsonString, Class<T> clazz) throws RuntimeException {
		Map<String, Map<String, Object>> map;
		try {
			map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Map<String, Object>>>() {
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Map<String, T> result = new HashMap<String, T>();
		for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * 深度转换json成map
	 *
	 * @param json
	 * @return
	 */
	public static Map<String, Object> json2mapDeeply(String json) throws RuntimeException {
		return json2MapRecursion(json, objectMapper);
	}

	/**
	 * 把json解析成list，如果list内部的元素存在jsonString，继续解析
	 *
	 * @param json
	 * @param mapper 解析工具
	 * @return
	 * @throws RuntimeException
	 */
	@SuppressWarnings("unchecked")
	private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) throws RuntimeException {
		if (json == null) {
			return null;
		}

		List<Object> list;
		try {
			list = mapper.readValue(json, List.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (Object obj : list) {
			if (obj != null && obj instanceof String) {
				String str = (String) obj;
				if (str.startsWith("[")) {
					obj = json2ListRecursion(str, mapper);
				} else if (obj.toString().startsWith("{")) {
					obj = json2MapRecursion(str, mapper);
				}
			}
		}

		return list;
	}

	/**
	 * 把json解析成map，如果map内部的value存在jsonString，继续解析
	 *
	 * @param json
	 * @param mapper
	 * @return
	 * @throws RuntimeException
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) throws RuntimeException {
		if (json == null) {
			return null;
		}

		Map<String, Object> map;
		try {
			map = mapper.readValue(json, Map.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object obj = entry.getValue();
			if (obj != null && obj instanceof String) {
				String str = ((String) obj);

				if (str.startsWith("[")) {
					List<?> list = json2ListRecursion(str, mapper);
					map.put(entry.getKey(), list);
				} else if (str.startsWith("{")) {
					Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
					map.put(entry.getKey(), mapRecursion);
				}
			}
		}

		return map;
	}

	/**
	 * 与javaBean json数组字符串转换为列表
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws RuntimeException {

		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		List<T> lst;
		try {
			lst = (List<T>) objectMapper.readValue(jsonArrayStr, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return lst;
	}

	/**
	 * 获取泛型的Collection Type
	 *
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses  元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * map 转JavaBean
	 */
	public static <T> T map2pojo(Map<?, ?> map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}

	/**
	 * map 转json
	 *
	 * @param map
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String mapToJson(Map<?, ?> map) {
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * map 转JavaBean
	 */
	public static <T> T obj2pojo(Object obj, Class<T> clazz) {
		return objectMapper.convertValue(obj, clazz);
	}

	/**
	 * 
	 * deep copy object
	 *
	 */
	public static Object copyObj(Object value) {
		String jsonStr = obj2json(value);
		try {
			return json2map(jsonStr);
		} catch (Exception e) {
			return value;
		}
	}
	
}
