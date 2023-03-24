package com.pet.project001.global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 在应用域初始化时创建数据池，将要链接的数据库的信息放进池子里(实现仅创建一次）
 */
@WebListener
public class LoadJdbcConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //应用域对象创建完毕
        InputStream is = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream("jdbc.properties");

        Properties prop = new Properties();

        try {
            prop.load(is);
            String driver = prop.getProperty("jdbc.driver");
            String url = prop.getProperty("jdbc.url");
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");
            //创建数据源
            Global.createDataSource(driver, url, user, password);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
