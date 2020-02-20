package controller.sjh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BM_MemberDTO;
import dto.QnADTO;
import singleton.Singleton;
@WebServlet("/mypageupdate")
public class MyPageUpdate extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}
	public void processFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String command = req.getParameter("command");
		System.out.println("JAVA: command: "+command);
		Singleton s = Singleton.getInstance();
		
		String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
			       + "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";
		
		
		if(command.equals("mypageUpdate")) {
			String id = req.getParameter("id");
			
			resp.sendRedirect(req.getContextPath()+"/JSP/mypagePwdCheck.jsp?id="+id);
			
		}else if(command.equals("mypagePwdChecked")){
			String loginId = req.getParameter("loginId");
			String pwd = req.getParameter("pwd");
				System.out.println("command: "+command+" / id: "+loginId+" / pwd: "+pwd);
			boolean isCorrect = s.myPageService.checkPassword(loginId, pwd);
		
			if(isCorrect) {
				
				BM_MemberDTO dto = s.myPageService.getMyPage(loginId);
				req.setAttribute("dto", dto);
				forward("/JSP/mypageupdate.jsp", req, resp);
				
			}else {

				resp.getWriter().println("<script>alert('인증 실패'); "+ addfunc
						                +" location.href=getContextPath() + '/mypagefoward?loginId=\"+loginId'</script>");
			}
		
		}else if(command.equals("mypageupdateAf")) {
			String loginId = req.getParameter("loginId");
			String pwd = req.getParameter("pwd");
			String pwdcheck = req.getParameter("pwdCheck");
			String phoneNum = req.getParameter("phoneNum");
			String email = req.getParameter("email");

			if(pwd.equals(pwdcheck)) {
				
				boolean isS = s.myPageService.mypageUpdate(loginId, pwd, phoneNum, email);
				if(isS) {
					resp.getWriter().println("<script>alert('회원정보가 수정되었습니다.'); "+ addfunc +" location.href=getContextPath() + '/mypagefoward'</script>");
				}else {
					resp.getWriter().println("<script>alert('수정 실패'); "+ addfunc +" location.href=getContextPath() + '/mypagefoward'</script>");
				}
			}
		}
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

}
