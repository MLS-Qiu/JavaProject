package com.pet.project001.dao.log.impl;

import com.pet.project001.dao.log.LoginDAO;
import com.pet.project001.global.Global;
import com.pet.project001.model.Pet;
import com.pet.project001.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.transform.Templates;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    private final JdbcTemplate template = new JdbcTemplate(Global.getDataSource());

    @Override
    public User findByName(String username) {
        String sql = "select * from t_user where username =?";
        List<User> userList = template.query(sql, new BeanPropertyRowMapper(User.class), username);

        if (userList.size() > 0) {
            User user = userList.get(0);
            return user;
        } else {
            return null;
        }

    }

    public boolean userUpdate(User user,String username) {
        String sqlUp = "update t_user set username=?,password=?,phone=?,photo=?,address=?,nickname=? " +
                "where username = ?";
        int rows = template.update(sqlUp, user.getUsername(), user.getPassword(), user.getPhone(),
                user.getPhoto(), user.getAddress(), user.getNickname(),username);
        if (rows > 0) {
            return true;
        } else {
            return false;
        }
    }
}
