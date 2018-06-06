/*  Data Analysis with Java  (Lis. 5-12)
 *  John R. Hubbard
 *  Apr 30, 2017
 */

package dawj.ch05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTester {
    private static final String URL = "jdbc:sqlite:C:\\dawj\\db\\library.data";
    private static final String USR = "dawj";  // 사용자 아이디 입력
    private static final String PWD = "dawj";  // 사용자 비밀번호 입력

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USR, PWD);
            Statement stmt = conn.createStatement();
            
            String sql = String.format("select name, city from Publishers");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String pubName = rs.getString("name");
                String pubCity = rs.getString("city");
                System.out.printf("%s, %s%n", pubName, pubCity);
            }
            rs.close();
            
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
