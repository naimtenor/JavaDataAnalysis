/*  Data Analysis with Java
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

/*  AuthorsBooks.dat 파일을 읽어 AuthorsBooks 테이블에 저장
 */
public class LoadAuthorsBooks {
	private static final String URL = "jdbc:sqlite:C:\\dawj\\db\\library.data";
    private static final String USR = "dawj";  // 데이터베이스 사용자 이름
    private static final String PWD = "dawj";  // 데이터베이스 비밀번호
    private static final File DATA = new File("data/AuthorsBooks.dat");
    private static final String SQL = "insert into AuthorsBooks values(?, ?)";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(URL, USR, PWD);
            PreparedStatement ps = conn.prepareStatement(SQL);
            Scanner fileScanner = new Scanner(DATA)){
            conn.createStatement().execute("delete from AuthorsBooks");
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
                String author = lineScanner.next();
                String book = lineScanner.next();
                ps.setString(1, author);
                ps.setString(2, book);
                rows += ps.executeUpdate();
                lineScanner.close();
            }
            System.out.printf("%d 행이 AuthorsBooks 테이블에 저장됨%n", rows);
            conn.close();
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}
