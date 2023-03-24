package com.mls.dao;

import com.mls.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDAO {
    @Select("select * from t_user")
    List<User> findAll();
}
