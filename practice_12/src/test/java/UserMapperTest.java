import com.yukicris.dao.UserMapper;
import com.yukicris.pojo.User;
import com.yukicris.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUsers();
        List<User> user2 = mapper.getUsers();
        for (User user1: user) {
            System.out.println(user);
        }
        sqlSession.close();
    }
}
