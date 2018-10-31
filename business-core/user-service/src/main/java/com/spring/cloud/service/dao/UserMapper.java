package com.spring.cloud.service.dao;

import com.spring.cloud.service.entity.User;
import com.spring.cloud.service.entity.UserExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}