import javax.servlet.http.HttpServlet;
import java.sql.*;

public class TestJdbc extends HttpServlet {

    public static void main(String[] args) throws
            ClassNotFoundException, SQLException {
        //useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/jdbc_learn?useUnicode=true&characterEncoding=utf-8";

        String username = "root";
        String password = "123456";

        //1 加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2 链接数据库,CONNECT代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //3 向数据库发送sql的对象statement(清单,报表): crud
        Statement statement = connection.createStatement();
        //4 编写sql
        String sql = "select * from users";
        //5 执行查询sql
        ResultSet resultSet = statement.executeQuery(sql);

        /**
         *  //4 编写sql
         *  String sql = "delete form users where id = "1";
         *  //5 执行查询sql,增删改都使用executeUpdate即可
         *  int i = statement.executeUpdate(sql);  // i为受影响的行数
         */
        //6 result结果集
        while(resultSet.next()){
            System.out.println("id="+resultSet.getObject("id"));
            System.out.println("name="+resultSet.getObject("name"));
            System.out.println("password="+resultSet.getObject("password"));
            System.out.println("email="+resultSet.getObject("email"));
            System.out.println("birthday="+resultSet.getObject("birthday"));
        }

        //6 关闭连接,释放资源先开后关
        resultSet.close();
        statement.close();
        connection.close();


    }

}
