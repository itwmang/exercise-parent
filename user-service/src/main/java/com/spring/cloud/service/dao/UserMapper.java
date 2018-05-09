package com.spring.cloud.service.dao;

import com.spring.cloud.service.entity.User;
import com.spring.cloud.service.entity.UserExample;
import java.util.List;

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