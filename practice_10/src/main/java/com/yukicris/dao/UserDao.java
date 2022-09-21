package com.yukicris.dao;

import com.yukicris.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> getUserList();

    int addUser(User user);

    int updateUser(User user);

    //企业里面不太规范但很实用的玩法
    //使用一个万能的map,不需要知道User里面有多少参数,,有100个我也不管
    int addUser2(Map<String,Object> map);
}
