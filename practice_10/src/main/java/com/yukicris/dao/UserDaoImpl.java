package com.yukicris.dao;

import com.yukicris.pojo.User;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao{
    public List<User> getUserList() {
        //以前用jdbc就要用这个jdbc来链接数据库写sql语句啥的,换成mybatis就可以直接通过mapper调用
        //执行sql
        String sql = "select * from user.users";
        //结果集
        //ResultSet
        return null;
    }

    public int addUser(User user) {
        return 0;
    }

    public int updateUser(User user) {
        return 0;
    }

    public int addUser2(Map<String, Object> map) {
        return 0;
    }
}
