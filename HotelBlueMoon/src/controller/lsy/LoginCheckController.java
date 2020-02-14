package controller.lsy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import singleton.Singleton;

@WebServlet("/login")
public class LoginCheckController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		Singleton s = Singleton.getInstance();
		boolean idCheck = s.memberService.idCheck(id);
		String sessionId = s.memberService.login(id, pw);
		if (idCheck) {
			if (sessionId != "") {
				resp.getWriter().print("success");
			} else if (sessionId == "") {
				resp.getWriter().print("pw false");
			}
		} else {
			resp.getWriter().print("id false");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}

}
