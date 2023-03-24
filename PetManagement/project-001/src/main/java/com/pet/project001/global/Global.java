package com.pet.project001.global;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

public class Global {
    //为了只创建一次，所以使用静态的
    private static DataSource ds;//数据库连接池
    public static final String USER_LOGIN_KEY = "user_login-key";
    static void createDataSource(String driver, String url, String user, String password) {
        //数据库连接池
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            Class<? extends Driver> clazz = (Class<? extends Driver>) Class.forName(driver);
            dataSource.setDriverClass(clazz);//clazz相当于Driver.class
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            ds = dataSource;//向上转型
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //作为Template的参数
    public static DataSource getDataSource(){
        return ds;
    }
}
