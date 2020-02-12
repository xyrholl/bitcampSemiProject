package controller.lsy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BM_MemberDTO;
import singleton.Singleton;

@WebServlet("/regiservlet")
public class RegiSubmitController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");

		BM_MemberDTO bm_memberDto = new BM_MemberDTO(id, pw, name, phone, email);

		Singleton s = Singleton.getInstance();
		boolean registCheck = s.memberService.registration(bm_memberDto);
		if (registCheck) {
			resp.getWriter().print("yes");
		} else {
			resp.getWriter().print("no");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}

}
