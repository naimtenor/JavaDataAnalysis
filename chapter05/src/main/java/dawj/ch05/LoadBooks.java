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

/*  Books.dat파일을 읽어 Books 테이블에 저장 
 */
public class LoadBooks {
	private static final String URL = "jdbc:sqlite:C:\\dawj\\db\\library.data";
    private static final String USR = "dawj";  // 데이터베이스 사용자 이름
    private static final String PWD = "dawj";  // 데이터베이스 비밀번호
    private static final File DATA = new File("data/Books.dat");
    private static final String SQL = 
            "insert into Books values(?, ?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(URL, USR, PWD);
            PreparedStatement ps = conn.prepareStatement(SQL);
            Scanner fileScanner = new Scanner(DATA)){
            conn.createStatement().execute("delete from AuthorsBooks");
            conn.createStatement().execute("delete from Books");
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter(",");
                String title = lineScanner.next();
                int edition = lineScanner.nextInt();
                String cover = lineScanner.next();
                String publisher = lineScanner.next();
                int pubYear = lineScanner.nextInt();
                String isbn = lineScanner.next();
                int numPages = lineScanner.nextInt();
                ps.setString(1, title);
                ps.setInt(2, edition);
                ps.setString(3, cover);
                ps.setString(4, publisher);
                ps.setInt(5, pubYear);
                ps.setString(6, isbn);
                ps.setInt(7, numPages);
                rows += ps.executeUpdate();
                lineScanner.close();
            }
            System.out.printf("%d 행이 Books 테이블에 저장됨%n", rows);
            conn.close();
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}
