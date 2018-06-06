/*  Data Analysis with Java  (Lis. 5-14)
 *  John R. Hubbard
 *  May 4, 2017
 */

package dawj.ch05;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


/*  Publishers.dat 파일을 읽어 Publishers 테이블 입력
 */
public class LoadPublishers {
	private static final String URL = "jdbc:sqlite:C:\\dawj\\db\\library.data";
    private static final String USR = "dawj";  // 데이터베이스 사용자 이름
    private static final String PWD = "dawj";  // 데이터베이스 비밀번호
    private static final File DATA = new File("data/Publishers.dat");
    private static final String SQL = 
            "insert into Publishers values(?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USR, PWD);
            PreparedStatement ps = conn.prepareStatement(SQL);
            Scanner fileScanner = new Scanner(DATA);
            conn.createStatement().execute("delete from AuthorsBooks");
            conn.createStatement().execute("delete from Books");
            conn.createStatement().execute("delete from Publishers");
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
                String id = lineScanner.next();
                String name = lineScanner.next();
                String city = lineScanner.next();
                String country = lineScanner.next();
                String url = (lineScanner.hasNext() ? lineScanner.next() : "");
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, city);
                ps.setString(4, country);
                if (url.length() > 0) {
                    ps.setString(5, url);
                } else {
                    ps.setNull(5, Types.VARCHAR);
                }
                rows += ps.executeUpdate();
                lineScanner.close();
            }
            System.out.printf("%d 행이 Publishers 테이블에 저장됨%n", rows);
            conn.close();
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}
