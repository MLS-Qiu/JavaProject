package com.pet.project001.service.log.impl;

import com.pet.project001.dao.log.LoginDAO;
import com.pet.project001.dao.log.impl.LoginDAOImpl;
import com.pet.project001.model.User;
import com.pet.project001.service.log.LoginService;
import com.pet.project001.util.BeanFactory;
import com.pet.project001.util.Md5Utils;

public class LoginServiceImpl implements LoginService {
    private final LoginDAO dao = BeanFactory.getBean(LoginDAOImpl.class);
    public User findByName(String username){
        //通过Dao实现
        return dao.findByName(username );
    }

    @Override
    public boolean checkLogin(User user, String password) {
        if (user == null){
            return false;
        }else {
            String encrypt = Md5Utils.encrypt(password + "{" + user.getUsername() + "}");//密码加密之后的密文
            if (encrypt.equals(user.getPassword())){
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public boolean checkUsername(String username) {
        User user = dao.findByName(username);
        if (user!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean userUpdate(User user,String username) {
        return dao.userUpdate(user,username);
    }

    @Override
    public boolean checkPassword(String p1, String p2) {
        if (p1.equals(p2)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String makePassword(String password,String username) {
        String encrypt = Md5Utils.encrypt(password + "{" + username + "}");//密码加密之后的密文
        return encrypt;
    }
}
