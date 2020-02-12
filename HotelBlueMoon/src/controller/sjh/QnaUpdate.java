package controller.sjh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QnADTO;
import singleton.Singleton;
@WebServlet("/qnaupdate")
public class QnaUpdate extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	
	public void processFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("------------------------qnaupdate");
		int seq = Integer.parseInt(req.getParameter("seq"));
		String command = req.getParameter("command");
		
		System.out.println(seq + "  " + command);
		
		if(command.equals("QnAUpdate")) {
			Singleton s = Singleton.getInstance();
			QnADTO dto = s.qnaService.getQnADetail(seq);
			
			req.setAttribute("dto", dto);
			forward("JSP/qnaupdate.jsp", req, resp);
			
		}else {
			// QnAUpdateAf
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			System.out.println(seq + " " + title + " " + content);
			
			Singleton s = Singleton.getInstance();
			boolean isS = s.qnaService.qnaUpdate(seq, title, content);
			resp.setContentType("text/html; charset=UTF-8");
			
			String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
					       + "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";
			
			if(isS) {
				resp.getWriter().println("<script>alert('수정 완료'); "+ addfunc +" location.href=getContextPath() + '/qnafoward'</script>");
				
			}else {
				resp.getWriter().println("<script>alert('수정 실패')</script>");
			}
			resp.getWriter().flush();
			
			
		}
		
			
	}
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
}
