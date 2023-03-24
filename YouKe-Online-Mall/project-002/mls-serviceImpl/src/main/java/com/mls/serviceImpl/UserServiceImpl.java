package com.mls.serviceImpl;

import com.mls.dao.UserDAO;
import com.mls.model.User;
import com.mls.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO dao;

    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }
}
