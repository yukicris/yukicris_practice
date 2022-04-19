import java.sql.*;

public class TestJdbc2 {
    public static void main(String[] args) throws
            Exception {
        //useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/jdbc_learn?useUnicode=true&characterEncoding=utf-8";

        String username = "root";
        String password = "123456";

        //1 加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2 链接数据库,CONNECT代表数据库,重点是DriverManager
        Connection connection = DriverManager.getConnection(url, username, password);

        //3 编写sql
        //String sql = "select * from users";
        String sql = "insert into users(id, name, password, email, birthday) values (?,?,?,?,?);";

        //预编译(这个更安全)
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,4);// 给第一个占位符?的值赋值为1
        preparedStatement.setString(2,"赵六");// 给第一个占位符?的值赋值为1
        preparedStatement.setString(3,"123456");// 给第一个占位符?的值赋值为1
        preparedStatement.setString(4,"zl@qq.com");// 给第一个占位符?的值赋值为1
        preparedStatement.setDate(5,new Date(new java.util.Date().getTime()));//

        // 执行预编译
        int i = preparedStatement.executeUpdate();

        if (i>0){
            System.out.println("插入成功");
        }



        //6 关闭连接,释放资源先开后关
        preparedStatement.close();
        connection.close();
    }
}
