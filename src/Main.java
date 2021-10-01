import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			//데이터 조회
			System.out.println("[데이터 조회]");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close();
			
			//데이터 추가
			System.out.println("[데이터 추가]");
			Statement stat2 = con.createStatement();
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, redate)" + "values ('방탄소년단', '남성', '2010', '2013', datetile('now', 'localtime'));";
			int cnt = stat2.executeUpdate(sql2);
			if(cnt>0) System.out.println("새로운 데이터가 추가되었습니다!");
			else System.out.println("[ERROR] 데이터 추가 오류!");
			stat2.close();
			
			//데이터 수정
			System.out.println("[데이터 수정]");
			Statement stat3 = con.createStatement();
			String sql3 = "update g_artists set a_year = '2000, 2010, 2020' " + "where id=1 ;";
			int cnt3 = stat3.executeUpdate(sql3);
			if(cnt3>0) System.out.println("데이터가 수정되었습니다!");
			else System.out.println("[ERROR] 데이터 수정 오류!");
			stat2.close();
			
			//데이터 삭제
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {}
			}
		}
	}

}
