package controller.sjh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import singleton.Singleton;
@WebServlet("/qnadelete")
public class QnaDelete extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	public void processFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int seq = Integer.parseInt(req.getParameter("seq"));
		Singleton s = Singleton.getInstance();
		boolean isS = s.qnaService.qnaDelete(seq);
		
		resp.setContentType("text/html; charset=UTF-8");
		
		String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
				       + "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";
		
		if(isS) {
			resp.getWriter().println("<script>alert('삭제 완료'); "+ addfunc +" location.href=getContextPath() + '/qnafoward' </script>");
			
		}else {
			resp.getWriter().println("<script>alert('삭제 실패'); "+ addfunc +" location.href=getContextPath() + '/qnafoward' </script>");
		}
		resp.getWriter().flush();
	}
	
}
