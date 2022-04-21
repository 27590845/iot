package com.xidian.iot.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * JSON转换工具,此类能够完成Object到String以及String到Object的相互转换。
 */
public class JsonUtil {

    private static Logger log = LoggerFactory.getLogger(com.xidian.iot.common.util.JsonUtil.class);

    /**
     * JSON转换器，所有JSON操作都依赖于此类。
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 静态块，当JsonUtil第一次被调用时执行，切只执行一次。
     */
    static {

        // 定义日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(dateFormat);
        // 反序列化时忽略多余的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // TODO:搞明白这几个属性
        // 忽略Null的值,节省空间.
        // mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        // 忽略Default值木有变化的属性,更节省空间,用于接收方有相同的Class
        // 如int属性初始值为0,那么这个属性将不会被序列化
        // mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
    }

    /**
     * 验证json格式是否合法。
     * @param json json字符串
     * @return true 合法json字符串，false 不合法json字符串。
     */
    public static boolean validate(String json) {
        return new com.xidian.iot.common.util.JsonValidator().validate(json);
    }

    /**
     * 获得JSON字符串字段的字符串形式
     * 注意：若字段的值是一个字符串，则返回时会多双引号。例{"name":"richard"},方法返回时如下:""richard""，而非 "richard"
     * @param jsonStr JSON字符串。
     * @param fieldName 字段名称。
     * @return 当找不到字段时，返回{@link com.xidian.iot.common.util.StringUtil#EMPTY}。
     *         当找到字段时，返回表示其字符串形式
     * @throws Exception 解析发生错误。
     */
    public static String getFieldString(String jsonStr, String fieldName) throws Exception {
        log.debug("getFiledString() executing jsonStr={},fieldName={}", jsonStr, fieldName);
        if (jsonStr == null) {
            return null;
        }
        JsonNode rootNode = readValue(jsonStr, JsonNode.class);
        if (rootNode == null) {
            log.error("readValue() return is null.jsonStr={}", jsonStr);
            return null;
        }

        JsonNode path = rootNode.path(fieldName);
        if (!path.isMissingNode()) {
            return path.toString();
        } else {
            log.error("path is missing node,fieldName={}", fieldName);
            return null;
        }
    }

    /**
     * 将对象转换为JSON字符串。
     * 已知问题：设User u; Agent a;Object o; 当u依赖a，a又依赖u，且o同时依赖u和a则不能正常转化
     * @param obj 需要转换的对象
     * @return 当转换成功，则返回JSON字符串
     * @throws Exception 解析发生错误
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        if (obj == null) {
            log.error("Prameter 'obj' is null.");
            return com.xidian.iot.common.util.StringUtil.EMPTY;
        }
        return mapper.writeValueAsString(obj);
    }

    /**
     * 将一个json字符串转化为一个java对象
     * @param <T>
     * @param jsonStr json格式的字符串
     * @param clazz 目标类型
     * @return 返回类型为T的对象
     * @throws Exception
     */
    public static <T> T toObject(String jsonStr, Class<? extends T> clazz) {
        if (jsonStr == null) {
            log.error("Prameter 'jsonStr' is null.");
            return null;
        }
        return readValue(jsonStr, clazz);
    }

    /**
     * 一个jsonStr包含多个java对象，取其中一个转化为java对象的方法
     * 
     * @param content json格式的字符串
     * @param key 要转换的子json串的key
     * @param clazz 目标类型
     * @return 返回类型为T的对象
     * @throws Exception
     */
    public static <T> T toObject(String content, String key, Class<? extends T> clazz) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return toObject(path.toString(), clazz);
        } else {
            return null;
        }
    }

    /**
     * 将JSON字符串转换为List，采用ArrayList返回。
     * 
     * @param <T> 元素类型
     * @param jsonStr 需要转换的JSON字符串
     * @param clazz 元素类型，与T类型相同
     * @return 当解析成功，返回一组clazz类型的列表。
     * 
     * @throws Exception 解析发生错误
     */
    @SuppressWarnings("deprecation")
    public static <T> List<T> toList(String jsonStr, Class<? extends T> clazz) throws Exception {
        if (jsonStr == null) {
            log.error("Prameter 'jsonStr' is null.");
            return null;
        }
        return readValue(jsonStr, mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
    }

    /**
     * 将JSON字符串的某一字段转换为List，采用ArrayList返回。
     * 
     * @param <T> 元素类型
     * @param jsonStr 需要转换的JSON字符串
     * @param fieldName 字段名称
     * @param clazz 元素类型，与T类型相同
     * @return 当解析成功，返回一组clazz类型的列表
     * @throws Exception 解析发生错误
     */
    public static <T> List<T> toList(String jsonStr, String fieldName, Class<? extends T> clazz) throws Exception {
        return toList(getFieldString(jsonStr, fieldName), clazz);
    }

    /**
     * 将JSON字符串转换为数组
     * @param <T> 元素类型。
     * @param jsonStr JSON字符串
     * @param clazz 元素类型，与T类型相同
     * @return 当解析成功，返回一个数组
     * @throws Exception 解析发生错误
     */
    @SuppressWarnings("deprecation")
    public static <T> T[] toArray(String jsonStr, Class<? extends T> clazz) throws Exception {
        if (jsonStr == null) {
            log.error("Prameter 'jsonStr' is null.");
            return null;
        }
        return readValue(jsonStr, mapper.getTypeFactory().constructArrayType(clazz));
    }

    /**
     * 将JSON字符串的某一字段转换为数组
     * @param <T> 元素类型。
     * @param jsonStr JSON字符串
     * @param fieldName 字段名称
     * @param clazz 元素类型，与T类型相同
     * @return 当解析成功，返回一个数组
     * @throws Exception 解析发生错误
     */
    public static <T> T[] toArray(String jsonStr, String fieldName, Class<? extends T> clazz) throws Exception {
        return toArray(getFieldString(jsonStr, fieldName), clazz);
    }

    /**
     * 将JSON字符串的某一字段转换为日期
     * @param jsonStr JSON字符串
     * @param fieldName 字段名称
     * @return 当转换成功，则返回日期。
     * 
     * @throws Exception 解析发生错误
     */
    public static Date toDate(String jsonStr, String fieldName) throws Exception {
        return toObject(getFieldString(jsonStr, fieldName), Date.class);
    }

    /**
     * 将JSON字符串的某一字段转换为整形。
     * 
     * @param jsonStr JSON字符串
     * @param fieldName 字段名称
     * @return 当转换成功，则返回整形。
     * 
     * @throws Exception 解析发生错误
     */
    public static Integer toInteger(String jsonStr, String fieldName) throws Exception {
        return toObject(getFieldString(jsonStr, fieldName), Integer.class);
    }

    /**
     * 将JSON字符串转换为整形数组。
     * 
     * @param content JSON字符串
     * @return 当转换成功，则返回整形数组。
     * 
     * @throws Exception 解析发生错误
     */
    public static Integer[] toIntegerArray(String content) throws Exception {
        return toArray(content, Integer.class);
    }

    /**
     * 将JSON字符串的某一字段转换为整形数组。
     * 
     * @param jsonStr JSON字符串
     * @param fieldName 字段名称
     * @return 当转换成功，则返回整形数组。
     * 
     * @throws Exception 解析发生错误
     */
    public static Integer[] toIntegerArray(String jsonStr, String fieldName) throws Exception {
        return toIntegerArray(getFieldString(jsonStr, fieldName));
    }

    /**
     * 将对象转换为JSON字符串，并写入OutputStream。
     * 
     * @param output 将被写入的输出流。
     * @param o 需要转换的对象。
     * @throws Exception 解析或写入发生错误
     * 
     */
    public static void writeJson(OutputStream output, Object o) throws Exception {
        mapper.writeValue(output, o);
    }

    /**
     * 将对象转换为JSON字符串，并写入Writer。
     * 
     * @param output 将被写入的输出流。
     * @param o 需要转换的对象。
     * @throws Exception 解析或写入发生错误
     */
    public static void writeJson(Writer output, Object o) throws Exception {
        mapper.writeValue(output, o);
    }

    /**
     * 读取一个字符串到JSON对象。
     * 
     * @param jsonStr JSON字符串
     * @param clazz JSON对象类型。
     * @return JSON对象。
     */
    private static <T> T readValue(String jsonStr, Class<? extends T> clazz) {
        T rootNode = null;
        try {
            rootNode = mapper.readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            log.error("read json pase error {}", jsonStr, e);
        } catch (JsonMappingException e) {
            log.error("read json maping error {}", jsonStr, e);
        } catch (IOException e) {
            log.error("read json io error {}", jsonStr, e);
        }
        return rootNode;
    }

    /**
     * 读取一个字符串到JSON对象。
     * 
     * @param jsonStr JSON字符串
     * @param valueType JSON对象类型。
     * @return JSON对象。
     */
    private static <T> T readValue(String jsonStr, JavaType valueType) {
        T rootNode = null;
        try {
            rootNode = mapper.readValue(jsonStr, valueType);
        } catch (JsonParseException e) {
            log.error("read json pase error {}", jsonStr, e);
        } catch (JsonMappingException e) {
            log.error("read json maping error {}", jsonStr, e);
        } catch (IOException e) {
            log.error("read json io error {}", jsonStr, e);
        }
        return rootNode;
    }
}
