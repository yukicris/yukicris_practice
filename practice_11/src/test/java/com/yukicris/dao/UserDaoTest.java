package com.yukicris.dao;


import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import yukicris.dao.UserDao;
import yukicris.pojo.User;
import yukicris.utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    @Test
    public void test(){
        //第一步,获得sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            //方式一 执行sql,一般这个dao现在叫做Mapper
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            List<User> userList = mapper.getUserList();

        /*方式二,不推荐使用,耦合度低了,限定的太死了
        List<User> userList = sqlSession.selectList("com.yukicris.dao.UserDao.getUserList");*/
            for (User user:userList) {
                System.out.println(user);
            }
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            sqlSession.close();
        }

        //关闭sqlsession
        sqlSession.close();
    }

    @Test
    public void addUser(){
        //第一步,获得sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //执行
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(4);
        user.setName("赵六");
        user.setPassword("1233333");
        int i = mapper.addUser(user);
        if (i>0){
            System.out.println(i+"成功");
        }
        sqlSession.commit();
        //关闭
        sqlSession.close();

        //增删改不会成功,是因为没有提交事务
    }

    @Test
    public void updateUser(){
        //第一步,获得sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //执行
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setEmail("zl@qq.com");
        user.setId(4);
        int i = mapper.updateUser(user);
        if (i>0){
            System.out.println(i+"成功");
        }
        //提交事务
        sqlSession.commit();
        //关闭
        sqlSession.close();
    }


    @Test
    public void addUser2(){
        //第一步,获得sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //执行
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 用map来传,就更加的自由,可以只传几个固定的参数
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid",5);
        map.put("username","田七");
        map.put("password","445566");
        int i = mapper.addUser2(map);
        if (i>0){
            System.out.println(i+"成功");
        }
        sqlSession.commit();
        //关闭
        sqlSession.close();
    }

    @Test
    public void getUserByLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<String, Integer>();
        stringIntegerHashMap.put("startIndex",0);
        stringIntegerHashMap.put("pageSize",1);
        List<User> userByLimit = mapper.getUserByLimit(stringIntegerHashMap);
        for (User user:userByLimit) {
            System.out.println(user);
        }
        sqlSession.close();
    }


    @Test
    public void getUserByRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //通过java代码层面实现分页
        RowBounds rowBounds = new RowBounds(1,2);
        List<User> selectList = sqlSession.selectList("yukicris.dao.UserDao.getUserByRowBounds",null,rowBounds);
        for (User user:selectList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

}
