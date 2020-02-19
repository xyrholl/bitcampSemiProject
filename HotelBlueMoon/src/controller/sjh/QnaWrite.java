package controller.sjh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import singleton.Singleton;
@WebServlet("/qnawrite")
public class QnaWrite extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}
	
	public void processFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getParameter("command");
		int memberseq = 0;
		String title = "";
		String content = "";
		
		resp.setContentType("text/html; charset=UTF-8");
		Singleton s = Singleton.getInstance();
		
		
		if(command.equals("qnawrite")) {
			String loginId = req.getParameter("loginId");
			memberseq = s.qnaService.getMemberseq(loginId);
			resp.sendRedirect(req.getContextPath()+"/JSP/qnawrite.jsp?memberseq="+memberseq);
		
		
		}else {
			//qnawriteAf
			memberseq = Integer.parseInt(req.getParameter("memberseq"));
			title = req.getParameter("title");
			content = req.getParameter("content");
			
			boolean isS = s.qnaService.QnAWrite(memberseq, title, content);
			
			String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
				       + "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";
			
			if(isS) {
				resp.getWriter().println("<script>alert('QnA 글쓰기 완료'); "+ addfunc +" location.href=getContextPath() + '/qnafoward' </script>");
				
			}else {
				resp.getWriter().println("<script>alert('QnA 글쓰기 실패'); "+ addfunc +" location.href=getContextPath() + '/qnafoward' </script>");
			}
				
		}
	}
}
