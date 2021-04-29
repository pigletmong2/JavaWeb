package dbserv02.ex02;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 @WebServlet("/member3")
public class MemberServlet3 extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("들어가요~");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("나갑니다~");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		String command=req.getParameter("command");
		
		MemberDAO dao = new MemberDAO(); /* sql문으로 조회할 memberdao 객체를 생성 */
		MemberVO vo=new MemberVO(); 
	
		if(command!=null&&command.equals("addMember")) {
			String _id=req.getParameter("id");
			String _pwd=req.getParameter("pwd");
			String _name=req.getParameter("name");
			String _email=req.getParameter("email");
			
			vo.setId(_id);
			vo.setPwd(_pwd);
			vo.setName(_name);
			vo.setEmail(_email);
			dao.addMember(vo);
			System.out.println("추가완료");
		}else if(command!=null&&command.equals("delMember")) {
			String id=req.getParameter("id");
			dao.delMember(id);
		}
		
		List<MemberVO> list = dao.listMembers(vo); /* listmembers()메서드로 회원 정보를 조회 */
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
		
		for (int i = 0; i < list.size(); i++) { /* 조회한 회원 정보를 for문과 <tr>태그를 이용해 리스트로 출력 */
			MemberVO memberVO=(MemberVO)list.get(i);
			String id=memberVO.getId();
			String pwd=memberVO.getPwd();
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			Date joinDate=memberVO.getJoinDate();
			out.print("<tr><td>"+id+"</td><td>"+pwd+"</td><td>"
					+ name+"</td><td>"+email+"</td><td>"
					+joinDate+"</td><td>"+"<a href='/pro07/member3?command=delMember&id="+id+"'>삭제</a></td></tr>");
		} /* 삭제 클릭시 command 값과 회원 ID를 서블릿으로 전송 */
		out.print("</table></body></html>");
		out.print("<a href='/pro07/memberForm.html'>새 회원 등록하기</a>");
	}
}
