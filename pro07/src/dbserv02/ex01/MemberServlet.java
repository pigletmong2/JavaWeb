package dbserv02.ex01;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
/* @WebServlet("/member2") */
public class MemberServlet extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("들어가요~");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("나갑니다~");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String command=request.getParameter("command");
		MemberDAO dao = new MemberDAO(); /* sql문으로 조회할 memberdao 객체를 생성 */
		MemberVO vo=new MemberVO(); 
	
		List<MemberVO> list = dao.listMembers(vo); /* listmembers()메서드로 회원 정보를 조회 */
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>");
		
		for (int i = 0; i < list.size(); i++) { /* 조회한 회원 정보를 for문과 <tr>태그를 이용해 리스트로 출력 */
			MemberVO memberVO=(MemberVO)list.get(i);
			String id=memberVO.getId();
			String pwd=memberVO.getPwd();
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			Date joinDate=memberVO.getJoinDate();
			out.print("<tr><td>"+id+"</td><td>"+pwd+"</td><td>"
					+ name+"</td><td>"+email+"</td><td>"
					+joinDate+"</td></tr>");
		}
		out.print("</table></body></html>");
	}

}
