package cn.cerish.filter;

import cn.cerish.annotation.SerializedField;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(basePackages = "cn.cerish.controller")
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    //包含项
    private String[] includes = {};
    //排除项
    private String[] excludes = {};

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //重新初始化为默认值
        includes = new String[]{};
        excludes = new String[]{};

        //判断返回的对象是单个对象，还是list，活着是map
        if(o==null){
            return null;
        }

        if(methodParameter.getMethod().isAnnotationPresent(SerializedField.class)){
            //获取注解配置的包含和去除字段
            SerializedField serializedField = methodParameter.getMethodAnnotation(SerializedField.class);
            includes = serializedField.includes();
            excludes = serializedField.excludes();
        }

        Object resObj = null;
        if (o instanceof List){
            //List
            List list = (List)o;
            resObj = handleList(list);
        }else{
            //Single Object
            resObj = handleSingleObject(o);
        }
        return resObj;
    }

    /**
     * 处理返回值是单个entity对象
     *
     * @param o
     * @return
     */
    private Object handleSingleObject(Object o){
        Map<String,Object> map = new HashMap<String, Object>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field:fields){
            //如果未配置表示全部的都返回
            if(includes.length == 0 && excludes.length == 0){
                String newVal = getNewVal(o, field);
                map.put(field.getName(), newVal);
            }else if(includes.length > 0){
                //有限考虑包含字段
                if(isStringInArray(field.getName(), includes)){
                    String newVal = getNewVal(o, field);
                    map.put(field.getName(), newVal);
                }
            }else{
                //去除字段
                if(excludes.length > 0){
                    if(!isStringInArray(field.getName(), excludes)){
                        String newVal = getNewVal(o, field);
                        map.put(field.getName(), newVal);
                    }
                }
            }

        }
        return map;
    }

    /**
     * 处理返回值是列表
     *
     * @param list
     * @return
     */
    private List handleList(List list){
        List retList = new ArrayList();
        for (Object o:list){
            Map map = (Map) handleSingleObject(o);
            retList.add(map);
        }
        return retList;
    }

    /**
     * 通过反射 获取 字段对应的值
     *
     */
    private String getNewVal(Object o, Field field){
        String newVal = "";
        try {
            field.setAccessible(true);
            Object val = field.get(o);

            if(val!=null){
                newVal = val.toString();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newVal;
    }

    /**
     * 判断该字段是否在 数组中
     *
     */
    private static boolean isStringInArray(String str, String[] array){
        for (String val:array){
            if(str.equals(val)){
                return true;
            }
        }
        return false;
    }
}
