package anusec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	PreparedStatement pstmt = null;
	Connection con = null;
	ResultSet rs = null;

	public void connect(String ip, String port, String database, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		System.out.println("Conneting to Database");
		String path = "jdbc:mysql://" + ip + ":" + port +"/" + database;
		con = DriverManager.getConnection(path, user, password);
		System.out.println("Success to connect Database");
	}
	
	public void test() throws SQLException {
		pstmt = con.prepareStatement("select * from anuctf1121_information");
		rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println("팀이름 : " + rs.getString("ANUCTF_Team_Name") + "	학생이름 : " + rs.getString("ANUCTF_Stu_Name"));
		}
	}
	
	public void finish() throws SQLException {
		if(pstmt == null) {
			pstmt.close();
		}
		if(con == null) {
			con.close();
		}
		if(rs == null) {
			rs.close();
		}
	}
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String ip,port,database,user,password;
		Main mi = new Main();
		mi.connect(ip=sc.next(), port=sc.next(), database=sc.next(), user=sc.next(), password=sc.next());
		mi.test();
		mi.finish();
		sc.close();
	}

}
