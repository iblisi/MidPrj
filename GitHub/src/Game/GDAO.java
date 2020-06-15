package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Korea{
	String ks;
	String km;
	
}
class Japan{
	String js;
	String jm;
}

public class GDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private int max;
	private String Random;

	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "kosea", "kosea2019a");
			System.out.println("접속" + con);

		} catch (Exception e) {
			System.out.println("DB접속오류 " + e);
		}
	}

	public String start() {
		String RH = null;
		connect();

		try {
			String sql = "SELECT MAX(NO) FROM HANJA ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString("Max(no)");
				max = Integer.parseInt(no);
			}

			int result = (int) ((Math.random() * max) + 1);
			Random = String.valueOf(result);
			System.out.println(Random);

			sql = "SELECT * FROM HANJA WHERE  Hanja.No ='" + Random + "'";

			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt : " + pstmt);
			rs = pstmt.executeQuery();
			System.out.println("rs : " + rs);

			while (rs.next()) {
				String Hanja = rs.getString("Hanja");
				RH = Hanja;
			}
		} catch (Exception e) {
//			System.out.println("select 오류 " + e);
			e.printStackTrace();
		}
		return RH;
	}

	public Japan selectJ() {
		Japan ja=new Japan();

		try {
			String sql = "SELECT * FROM JAPANKANJI WHERE JAPANKANJI.NO ='" + Random + "'";
			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt : " + pstmt);
			rs = pstmt.executeQuery();
			System.out.println("rs : " + rs);

			while (rs.next()) {
				String Jsound = rs.getString("Jsound");
				String Jmean = rs.getString("Jmean");
				if (Jsound == null) {
					Jsound = " ";
				}
				if (Jmean == null) {
					Jmean = " ";
				}

				ja.js=Jsound;
				ja.jm=Jmean;
				System.out.println(Jmean + "\t" + Jsound);

			}
		} catch (Exception e) {
//			System.out.println("select 오류 " + e);
			e.printStackTrace();
		}
		return ja;
	}

	public Korea selectK() {
		
		Korea kr=new Korea();
		try {
			String sql = "SELECT * FROM  KOREAKANJI WHERE KOREAKANJI.NO  ='" + Random + "'";
			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt : " + pstmt);
			rs = pstmt.executeQuery();
			System.out.println("rs : " + rs);

			while (rs.next()) {
				String Ksound = rs.getString("Ksound");
				String Kmean = rs.getString("Kmean");
				
				kr.ks=Ksound;
				kr.km=Kmean;
				
				System.out.println(Kmean + "\t" + Ksound);

			}
		} catch (Exception e) {
//			System.out.println("select 오류 " + e);
			e.printStackTrace();
		}
		return kr;
	}

}
