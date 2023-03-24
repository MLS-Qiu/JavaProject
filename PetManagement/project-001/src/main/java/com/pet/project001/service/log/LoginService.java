package com.pet.project001.service.log;

import com.pet.project001.model.User;

public interface LoginService {
    public User findByName(String username);
    public boolean checkLogin(User username,String password);
    public boolean checkUsername(String username);

    public boolean userUpdate(User user,String username);

    boolean checkPassword(String p1,String p2);

    String makePassword(String password, String username);
}
