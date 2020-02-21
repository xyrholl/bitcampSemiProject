package controller.jdy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import singleton.Singleton;
@WebServlet("/paysuccess")
public class PaySuccess extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		System.out.println("kakao.jsp-> paysuccess호출");
		//location.href='<%=request.getContextPath()%>/paysuccess?resvSeq=<%=resvSeq%>'; 
		
		String sresvSeq = req.getParameter("resvSeq");
		System.out.println("paySuccess의 시퀀스:" +  sresvSeq);
		int resvSeq = Integer.parseInt(sresvSeq);
		//dao에서 paymentis update 1하기
		
		Singleton s = Singleton.getInstance();
		boolean b = s.resvSerivce.updatePaymentIs(resvSeq);
		
		String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
			       + "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";

		
		if(b ==true ) {
			System.out.println("paymentis성공");
			resp.getWriter().println("<script>alert('결제가 완료되었습니다. \\n 예약 내역은 마이페이지에서 확인해주세요.'); "+ addfunc +" location.href=getContextPath() + '/mypagefoward'</script>");
		}else {
			System.out.println("paymentis실패.....");
		}
		
	}
	
	
}
