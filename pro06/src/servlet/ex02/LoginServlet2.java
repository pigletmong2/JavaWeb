package servlet.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet2
 */
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
		/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");/* 응답할 데이터종류가 html임을 설정 */
		PrintWriter out = response.getWriter(); /* response 객체의 getwriter()를 이용해 출력 스트림 printwriter객체를 받아옴 */
		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		
		String data = "<html>";/* 브라우저로 출력할 데이터를 문자열로 연결해서 html태그로 만듬 */
			data +="<body>";
			data +="아이디 : "+id;
			data +="<br>";
			data +="패스워드 : "+pw;
			data +="</body>";
			data +="</html>";
			out.print(data); /* printwriter의 print()를 이용해 html태그문자열을 웹브라우저로 출력 */
					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
