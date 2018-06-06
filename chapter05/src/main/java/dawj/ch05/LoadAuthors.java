/*  Data Analysis with Java  (Lis. 5-15)
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

/*  Authors.dat 파일을 읽어 Authors 테이블에 입력 
 */

public class LoadAuthors {
	private static final String URL = "jdbc:sqlite:C:\\dawj\\db\\library.data";
    private static final String USR = "dawj";  // 데이터베이스 사용자 이름
    private static final String PWD = "dawj";  // 데이터베이스 비밀번호
    private static final File DATA = new File("data/Authors.dat");
    private static final String SQL = "insert into Authors values(?, ?, ?, ?)";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USR, PWD);
            PreparedStatement ps = conn.prepareStatement(SQL);
            Scanner fileScanner = new Scanner(DATA);
            conn.createStatement().execute("delete from AuthorsBooks");
            conn.createStatement().execute("delete from Authors");
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
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
            conn.close();
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}
