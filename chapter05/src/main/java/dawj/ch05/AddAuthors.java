/*  Data Analysis with Java  (Lis. 5-13)
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
import java.util.Scanner;

/*  Authors.dat 파일에서 데이터를 읽어 Authors 테이블에 기록한다.
 */
public class AddAuthors {
    private static final String URL = "jdbc:sqlite:C:\\dawj\\db\\library.data";
    private static final String USR = "dawj";  // 데이터베이스 사용자 이름
    private static final String PWD = "dawj";  // 데이터베이스 비밀번호

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USR, PWD);
            File file = new File("data/Authors1.dat");
            String sql = "insert into Authors values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            Scanner fileScanner = new Scanner(file);
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter(",");
                String id = lineScanner.next();
                String lastName = lineScanner.next();
                String firstName = lineScanner.next();
                int yob = lineScanner.nextInt();
                ps.setString(1, id);
                ps.setString(2, lastName);
                ps.setString(3, firstName);
                ps.setInt(4, yob);
                rows += ps.executeUpdate();
                lineScanner.close();
            }
            System.out.printf("%d 행이 Authors 테이블에 저장됨%n", rows);
            fileScanner.close();
            conn.close();
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}

