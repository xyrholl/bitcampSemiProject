package controller.lsy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BM_MemberDTO;
import dto.ReviewDTO;
import singleton.Singleton;

@WebServlet("/reviewInsert")
public class ReviewInsertController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sloginId = req.getParameter("loginId");
		String sresvSeq = req.getParameter("resvSeq");
		String shotelSeq = req.getParameter("hotelSeq");
		String sroomSeq = req.getParameter("roomSeq");
		String srating = req.getParameter("rating");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		Singleton s = Singleton.getInstance();
		BM_MemberDTO memDto = s.memberService.selectOneMember(sloginId);

		ReviewDTO dto = new ReviewDTO(Integer.parseInt(shotelSeq), Integer.parseInt(sroomSeq),
				Integer.parseInt(sresvSeq), memDto.getSeq(), title, content, Double.parseDouble(srating));
		boolean is = s.reviewService.insertReview(dto);
		
		if(is) {
			resp.sendRedirect(req.getContextPath() + "/JSP/review.jsp");
		}else {
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
