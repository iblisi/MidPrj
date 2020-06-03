import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // localhost��� ip�ּҰ� ������
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

			String query = "SELECT * FROM KOREAKANJI WHERE " +ms + "'";// ���� ��ȸ��
			
			rs = stmt.executeQuery(query); // �������� �о ����

			while (rs.next()) { // ����� ������ ����
				int NO = rs.getInt("NO"); // ���ڵ带 �о��
				String KSound = rs.getString("KSound"); // ���ڵ带 �о��
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
			System.out.println("�ε� ����");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println(conn.isClosed() ? "��������" : "������");// ������(false), ��������(true)
			stmt = conn.createStatement();
			System.out.println("�����˻�");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
