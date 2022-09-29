package com.yukicris.dao;

import com.yukicris.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user.users")
    List<User> getUsers();
}
