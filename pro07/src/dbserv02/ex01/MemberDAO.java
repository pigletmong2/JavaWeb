package dbserv02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	/*
	 * private static final String driver = "oracle.jdbc.driver.OracleDriver"; 작성시
	 * private static final까지 입력해야함. private static final String
	 * url="jdbc:oracle:thin:@localhost:1521:XE"; private static final String
	 * user="c##madang"; private static final String pwd="madang";
	 */

	public MemberDAO() {
		
			try {
				Context ctx=new InitialContext();
				Context envContext = (Context) ctx.lookup("java:/comp/env"); /* JNDI 접근을 위해 기본 경로 지정 */
				dataFactory = (DataSource) envContext
						.lookup("jdbc/oracle"); /* 톰캣context.xml에 설정한 name 값을 이용해 톰캣이 미리 연결한 datasource를 받아옴 */
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public List<MemberVO> listMembers(MemberVO vo2) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			/* connDB(); */
			con=dataFactory.getConnection();
			String query = "select*from t_member ";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query); /* sql문으로 회원정보 조회 */
			while (rs.next()) {/* 조회한 레코드 각 컬럼 값 받아오기 */
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date JoinDate = rs.getDate("joinDate");

				MemberVO vo = new MemberVO(); /* 각 컬럼 값을 다시 MemberVO 객체의 속성에 설정 */
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(JoinDate);
				list.add(vo); /* 설정된 MemberVO객체를 다시 ArrayList에 저장 */
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * private void connDB() throws ClassNotFoundException { try {
	 * Class.forName(driver); System.out.println("Oracle 드라이버 로딩 성공"); con =
	 * DriverManager.getConnection(url, user, pwd);
	 * System.out.println("Connection 생성 성공"); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

}
