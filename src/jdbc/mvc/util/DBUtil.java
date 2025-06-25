package jdbc.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // 데이터 베이스 접속 정보
    private static String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String dbID = "scott_05";       // 계정
    private static String dbPassword = "tiger";    // 비밀번호

    // 데이터베이스 접속
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbID, dbPassword);
    }

}
