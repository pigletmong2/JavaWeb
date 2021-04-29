package dbserv02.ex02;

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

	public void addMember(MemberVO memberVO) {
		try {
			con=dataFactory.getConnection();
			String id=memberVO.getId();
			String pwd=memberVO.getPwd();
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			
			String query="insert into t_member";
			query+=" (id,pwd,name,email)";
			query+=" values(?,?,?,?)";
			System.out.println("prepareStatement: "+query);
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delMember(String id) {
		try {
			con=dataFactory.getConnection();
			
			String query="delete from t_member"+" where id=?";
			System.out.println("prepareStatement:"+query);
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
