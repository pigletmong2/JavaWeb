package dbserv.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private Statement stmt;
	private Connection con;
	private static final String driver = "oracle.jdbc.driver.OracleDriver"; /* 작성시 private static final까지 입력해야함. */
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="c##madang";
	private static final String pwd="madang";
	
	public List<MemberVO> listMembers(MemberVO vo2){
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			connDB();
			String query="select*from t_member ";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query); /* sql문으로 회원정보 조회 */
			while (rs.next()) {/* 조회한 레코드 각 컬럼 값 받아오기 */
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				Date JoinDate=rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO(); /* 각 컬럼 값을 다시 MemberVO 객체의 속성에 설정 */
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(JoinDate);
				list.add(vo); /* 설정된 MemberVO객체를 다시 ArrayList에 저장 */
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void connDB() throws ClassNotFoundException {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con=DriverManager.getConnection(url,user,pwd);
			System.out.println("Connection 생성 성공");
			stmt=con.createStatement();
			System.out.println("Statement 생성 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
