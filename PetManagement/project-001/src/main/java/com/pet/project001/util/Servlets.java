package com.pet.project001.util;

import com.alibaba.fastjson2.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Servlets {
    public static void renderJson(String str, HttpServletResponse resp) {
        /*将转换为的json数组传回ajax*/
        /*1、设置响应内容的格式*/
        resp.setContentType("application/json;charset=utf-8");

        /*2、将内容传回ajax*/
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
            pw.write(str);
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void renderJson(Object obj, HttpServletResponse resp) {
        renderJson(JSON.toJSONString(obj),resp);
    }

    //将String数组转换为Integer数组
    public static Integer[] strToIn(String []sts){
        int len = sts.length;
        Integer[] ins = new Integer[len];
        for (int i=0;i<len;i++){
            ins[i] = Integer.parseInt(sts[i]);
        }
        return ins;
    }

}
