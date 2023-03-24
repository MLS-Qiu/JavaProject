package com.pet.project001.dao.log;

import com.pet.project001.model.User;

public interface LoginDAO {
    User findByName(String username);
    boolean userUpdate(User user,String username);

}
