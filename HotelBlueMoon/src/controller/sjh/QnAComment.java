package controller.sjh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QnADTO;
import singleton.Singleton;
@WebServlet("/qnacomment")
public class QnAComment extends HttpServlet {

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
		Singleton s = Singleton.getInstance();
		String command = req.getParameter("command");
		int seq;
		if(command.equals("qnacomment")) {
			seq = Integer.parseInt(req.getParameter("seq"));
			QnADTO dto =  s.qnaService.getQnADetail(seq);
			
			req.setAttribute("dto", dto);
			forward("JSP/qnacomment.jsp", req, resp);
			
		}else{	// qnacommentAf
			seq = Integer.parseInt(req.getParameter("seq"));
			String loginId = req.getParameter("loginId");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			System.out.println("JAVA: "+seq + " / "+loginId+" / " + title + " / " + content);
			
			boolean isS = s.qnaService.qnaComment(seq, loginId, title, content);
			
			String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
				       + "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";
			
			if(isS) {
				resp.getWriter().println("<script>alert('QnA 답글쓰기 완료'); "+ addfunc +" location.href=getContextPath() + '/qnafoward' </script>");
				
			}else {
				resp.getWriter().println("<script>alert('QnA 답글쓰기 실패'); "+ addfunc +" location.href=getContextPath() + '/qnafoward' </script>");
			}
			
		}
		
		
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
}
