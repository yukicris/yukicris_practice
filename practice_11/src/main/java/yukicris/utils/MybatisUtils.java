package yukicris.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//sqlSessionFactory生产sqlSession
public class MybatisUtils {
    //放到外头提升下作用域
    private static SqlSessionFactory sqlSessionFactory;

    static{
        try {
            //下面3句是死的,官方定义的
            //使用mybatis的第一步,获取sqlSessionFactory对象
            String resource = "mbatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //既然有了SqlSessionFactory,顾名思义,可以从中获得SqlSession 的实例了

    //SqlSession完全包含面向数据库执行sql命令的所有方法
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
