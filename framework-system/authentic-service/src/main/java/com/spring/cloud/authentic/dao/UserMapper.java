package com.spring.cloud.authentic.dao;

import com.spring.cloud.authentic.entity.User;
import com.spring.cloud.authentic.entity.UserExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}