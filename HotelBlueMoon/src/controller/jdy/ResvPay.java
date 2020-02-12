package controller.jdy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/resvPay")

public class ResvPay extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("pay호출");
//		<input type="text" name="name" placeholder="Name" value="<%=resv.getMemName() %>"/>
//		 <input type="text" name="phone" placeholder="Phone" value="<%=resv.getPhoneNum()%>"/> 
//		<input type="text" name="email" placeholder="E-mail" value="<%=resv.getEmail() %>" /> 
//		<input type="hidden" name="totalprice" value="<%=resv.getTotalPrice() %>"> 
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String stotalprice = req.getParameter("totalprice");
		int totalprice = Integer.parseInt(stotalprice);
		System.out.println(name+phone+email+totalprice);
		
		req.setAttribute("name", name);
		req.setAttribute("phone", phone);
		req.setAttribute("email", email);
		req.setAttribute("totalprice", totalprice);
		forward("JSP/kakao.jsp", req, resp);
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
}
