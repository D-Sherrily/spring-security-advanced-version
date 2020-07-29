package com.you.springsecuritydemo.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: ResponseUtil
 * @Description: response 工具类
 * @author: D
 * @create: 2020-07-20 16:50
 **/

public class ResponseUtil {

    public static void responseJson(HttpServletResponse response,int status ,Object data){
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);
            response.getWriter().write(JSONObject.toJSONString(data));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
