package com.pet.project001.util;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

//作用：一个类只生成一个实例
public class BeanFactory {
    private static final Map<Class<?>,Object> INSTANCE = new HashMap<>();

    //创建一个class返回他的唯一实例（一个类创建太多实例不好）
    public static <T> T getBean(Class<T> clazz){
        //得到了一个class实例
        T t = (T)INSTANCE.get(clazz);
        //如果这个类已经有实例了就直接返回这个实例
        if (t!=null){
            return t;
        }
        /*如果这个类没有实例，就新建一个实例*/
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            t =constructor.newInstance();
            INSTANCE.put(clazz,t);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
