import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // localhost대신 ip주소가 들어갈수도
	String id = "kosea";
	String pw = "kosea2019a";

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	KDAO() {
		try {
			DBC();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<KVO> list(String ms) {
		ArrayList<KVO> list = new ArrayList<KVO>();

		
		try {
			DBC();

			String query = "SELECT * FROM KOREAKANJI WHERE " +ms + "'";// 쿼리 조회문
			
			rs = stmt.executeQuery(query); // 쿼리문을 읽어서 저장

			while (rs.next()) { // 저장된 쿼리를 읽음
				int NO = rs.getInt("NO"); // 레코드를 읽어옴
				String KSound = rs.getString("KSound"); // 레코드를 읽어옴
				String KMean = rs.getNString("KMean");

				KVO data = new KVO(NO, KMean, KSound);
				list.add(data);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public void DBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로딩 성공");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println(conn.isClosed() ? "접속종료" : "접속중");// 접속중(false), 접속종료(true)
			stmt = conn.createStatement();
			System.out.println("쿼리검색");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
