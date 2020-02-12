package controller.sjh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BM_MemberDTO;
import singleton.Singleton;
@WebServlet("/mypagefoward")
public class MyPage extends HttpServlet {

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
		String loginId = (String)req.getSession().getAttribute("loginId");
		
		String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
			       + "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";
		
		if(loginId == null || loginId.equals("")) {
			resp.getWriter().println("<script>alert('로그인이 필요한 페이지입니다'); "+ addfunc
					                +" location.href=getContextPath() + '/fowardlogin'</script>");
			
		}else {
			
			Singleton s = Singleton.getInstance();
			BM_MemberDTO dto = s.myPageService.getMyPage(loginId);
			
			int reviewCount = s.myPageService.getMyReviewCount(loginId);
			int qnaCount = s.myPageService.getMyQnACount(loginId);
			int resvCount = s.myPageService.getMyResvCount(loginId);
			
			System.out.println("JAVA: reviewCount="+reviewCount+" / qnaCount="+qnaCount );
			
			req.setAttribute("dto", dto);
			req.setAttribute("reviewCount", reviewCount);
			req.setAttribute("qnaCount", qnaCount);
			req.setAttribute("resvCount", resvCount);
			forward("JSP/mypage.jsp", req, resp);

		}
		
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
}
