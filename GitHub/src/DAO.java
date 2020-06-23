import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class DAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	DefaultTableModel model = new DefaultTableModel(new String[] { "훈 독", "음 독", "훈 독", " 음 독","한자" }, 0) {
		public boolean isCellEditable(int i, int c){ return false; }
	};
	
	
	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "kosea", "kosea2019a");
//			System.out.println("접속" + con);

		} catch (Exception e) {
			System.out.println("DB접속오류 " + e);
		}
	}

	public void selectKs(String ms) {
		connect();
		try {

			String sql = "SELECT * FROM JAPANKANJI, KOREAKANJI, HANJA WHERE KOREAKANJI.NO = JAPANKANJI.NO AND KOREAKANJI.NO = HANJA.NO "
					+"AND ksound LIKE '%"+ms+"%'";
			
			pstmt = con.prepareStatement(sql);
//			System.out.println("pstmt : " + pstmt);
			rs = pstmt.executeQuery();
//			System.out.println("rs : " + rs);

			while (rs.next()) {
				String Kmean = rs.getString("Kmean");
				String Ksound = rs.getString("Ksound");
				String no = rs.getString("no");
				String Jsound = rs.getString("Jsound");
				String Jmean = rs.getString("Jmean");
				String Hanja = rs.getString("Hanja");

				if (Jsound == null) {
					Jsound = " ";
				}
				if (Jmean == null) {
					Jmean = " ";
				}

				Object data[] = { Kmean, Ksound, Jmean, Jsound, Hanja, no };
				
				model.addRow(data);
				
			}
		} catch (Exception e) {
//			System.out.println("select 오류 " + e);
			e.printStackTrace();

		}

	}

	public void selectKm(String ms) {
		connect();
		try {

			String sql = "SELECT * FROM JAPANKANJI, KOREAKANJI, HANJA WHERE KOREAKANJI.NO = JAPANKANJI.NO AND KOREAKANJI.NO = HANJA.NO "
					+"AND kmean LIKE '%"+ms+"%'";
			pstmt = con.prepareStatement(sql);
//			System.out.println("pstmt : " + pstmt);
			rs = pstmt.executeQuery();
//			System.out.println("rs : " + rs);

			while (rs.next()) {
				String Kmean = rs.getString("Kmean");
				String Ksound = rs.getString("Ksound");
				String no = rs.getString("no");
				String Jsound = rs.getString("Jsound");
				String Jmean = rs.getString("Jmean");
				String Hanja = rs.getString("Hanja");

				if (Jsound == null) {
					Jsound = " ";
				}
				if (Jmean == null) {
					Jmean = " ";
				}

				Object data[] = { Kmean, Ksound, Jmean, Jsound, Hanja, no };
				model.addRow(data);

			}
		} catch (Exception e) {
//			System.out.println("select 오류 " + e);
			e.printStackTrace();
		}
	}

	public void selectJs(String ms) {
		connect();
		String sql=null;
		try {

			if(ms.length()==1) {
				sql = "SELECT * FROM JAPANKANJI LEFT OUTER JOIN KOREAKANJI USING (no) LEFT OUTER JOIN HANJA USING (no) "
						+ "WHERE JSound = '"+ms+"' OR JSOUND like '%、"+ms+"、%' OR JSOUND like '%、"+ms+"' OR JSOUND like '"+ms+"、%'";
			}else if(ms.length()==2){
				sql = "SELECT * FROM JAPANKANJI LEFT OUTER JOIN KOREAKANJI USING (no) LEFT OUTER JOIN HANJA USING (no) "
						+ "WHERE JSound = '"+ms+"' OR JSOUND like '%、"+ms+"、%' OR JSOUND like '%、"+ms+"' OR JSOUND like '"+ms+"、%'";

			}else {
				sql = "SELECT * FROM JAPANKANJI LEFT OUTER JOIN KOREAKANJI USING (no) LEFT OUTER JOIN HANJA USING (no) "
						+ "WHERE JSound = '"+ms+"' OR JSOUND like '%、"+ms+"' OR jsound LIKE '%"+ms+"％'";
			}

			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt : " + pstmt);
			rs = pstmt.executeQuery();
			System.out.println("rs : " + rs);

			while (rs.next()) {
				String Kmean = rs.getString("Kmean");
				String Ksound = rs.getString("Ksound");
				String no = rs.getString("no");
				String Jsound = rs.getString("Jsound");
				String Jmean = rs.getString("Jmean");
				String Hanja = rs.getString("Hanja");

				if (Jsound == null) {
					Jsound = " ";
				}
				if (Jmean == null) {
					Jmean = " ";
				}

				Object data[] = { Jmean, Jsound, Kmean, Ksound, Hanja, no };

				model.addRow(data);
				
			}
		} catch (Exception e) {
//			System.out.println("select 오류 " + e);
			e.printStackTrace();
		}

	}

	public void selectJm(String ms) {
		connect();
		try {
			String sql=null;
			if(ms.length()==1) {
				sql = "SELECT * FROM JAPANKANJI, KOREAKANJI, HANJA WHERE KOREAKANJI.NO = JAPANKANJI.NO AND KOREAKANJI.NO = HANJA.NO "
						+"AND Jmean LIKE '"+ms+"'";

			}else {
				 sql = "SELECT * FROM JAPANKANJI, KOREAKANJI, HANJA WHERE KOREAKANJI.NO = JAPANKANJI.NO AND KOREAKANJI.NO = HANJA.NO "
						+"AND Jmean LIKE '%"+ms+"%'";
			}


			pstmt = con.prepareStatement(sql);
//			System.out.println("pstmt : " + pstmt);
			rs = pstmt.executeQuery();
//			System.out.println("rs : " + rs);

			while (rs.next()) {
				String Kmean = rs.getString("Kmean");
				String Ksound = rs.getString("Ksound");
				String no = rs.getString("no");
				String Jsound = rs.getString("Jsound");
				String Jmean = rs.getString("Jmean");
				String Hanja = rs.getString("Hanja");

				if (Jsound == null) {
					Jsound = " ";
				}
				if (Jmean == null) {
					Jmean = " ";
				}

				Object data[] = { Jmean, Jsound, Kmean, Ksound, Hanja, no };
				model.addRow(data);
				
			}
		} catch (Exception e) {
//			System.out.println("select 오류 " + e);
			e.printStackTrace();
		}
	}

}
