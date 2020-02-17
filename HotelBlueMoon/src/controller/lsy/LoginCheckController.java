package controller.lsy;

import java.io.IOException;
import java.net.URLEncoder;

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
		resp.setContentType("text/html; charset=utf-8");

		Singleton s = Singleton.getInstance();
		String id = req.getParameter("id");
		System.out.println("loginJava:" + id);
		String pw = req.getParameter("pw");
		System.out.println("loginJava" + pw);

		String checkin = URLEncoder.encode(req.getParameter("checkin"), "UTF-8");
		String checkout = URLEncoder.encode(req.getParameter("checkout"), "UTF-8");
		String guest = URLEncoder.encode(req.getParameter("guest"), "UTF-8");
		String hotelSeq = URLEncoder.encode(req.getParameter("hotelSeq"), "UTF-8");
		System.out.println("11로그인 컨트롤러의" + checkin + checkout + guest + hotelSeq);

		if (checkin.equals("null")) {
			System.out.println("아래");
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
		} else {
			System.out.println("위");
			boolean idCheck = s.memberService.idCheck(id);
			String sessionId = s.memberService.login(id, pw);
			if (idCheck) {
				if (sessionId != "") {
					resp.getWriter().print("resvSucces");
				} else if (sessionId == "") {
					resp.getWriter().print("pw false");
				}
			} else {
				resp.getWriter().print("id false");
			}
		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}

}
