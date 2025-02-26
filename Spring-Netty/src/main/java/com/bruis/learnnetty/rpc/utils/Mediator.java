package com.bruis.learnnetty.rpc.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储RPC中的映射以及方法Bean
 *
 * @author lhy
 * @date 2022/2/17
 */
@Slf4j
public class Mediator {

    public static Map<String, MethodBean> methodBeans;

    static {
        methodBeans = new HashMap<>();
    }

    public static Response process(RequestFuture future) {
        Response response = new Response();
        try {
            String path = future.getPath();
            MethodBean methodBean = methodBeans.get(path);
            if (null != methodBean) {
                Object bean = methodBean.getBean();
                Method method = methodBean.getMethod();
                Object request = future.getRequest();
                Class[] parameterTypes = method.getParameterTypes();
                // 此处只支持一个参数，所以写死固定0为索引
                Class parameterType = parameterTypes[0];
                Object param;
                // 如果参数是List类型
                if (parameterType.isAssignableFrom(List.class)) {
                    param = JSONArray.parseArray(JSONArray.toJSONString(request), parameterType);
                } else if (parameterType.getName().equalsIgnoreCase(String.class.getName())) {
                    param = request;
                } else {
                    param = JSONObject.parseObject(JSONObject.toJSONString(request), parameterType);
                }
                // 反射调用方法
                Object result = method.invoke(bean, param);
                response.setResult(result);
            }
        } catch (Exception e) {
            log.info("e: {}", e.getMessage());
        }
        response.setId(future.getId());
        return response;
    }

    @Data
    public static class MethodBean {

        private Object bean;

        private Method method;

    }
}
