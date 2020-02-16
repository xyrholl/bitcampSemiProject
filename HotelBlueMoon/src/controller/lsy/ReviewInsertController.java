package controller.lsy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.BM_MemberDTO;
import dto.ReviewDTO;
import singleton.Singleton;

@WebServlet("/insertreview")
public class ReviewInsertController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String saveDirectory = req.getSession().getServletContext().getRealPath("UPload");
		System.out.println(saveDirectory);
		int maxSize = 1024 * 1024 * 10;
		String encoding = "UTF-8";

		MultipartRequest multipartReq = new MultipartRequest(req, saveDirectory, maxSize, encoding,
				new DefaultFileRenamePolicy());

		String fileName = multipartReq.getOriginalFileName("imageFile");
		String fileRealName = multipartReq.getFilesystemName("imageFile");

		String sloginId = multipartReq.getParameter("loginId");
		String sresvSeq = multipartReq.getParameter("resvSeq");
		String shotelSeq = multipartReq.getParameter("hotelSeq");
		String sroomSeq = multipartReq.getParameter("roomSeq");
		String srating = multipartReq.getParameter("rating");
		String title = multipartReq.getParameter("title");
		String content = multipartReq.getParameter("content");
		String nowTime = multipartReq.getParameter("nowTime");

		if (fileName != null) {
			int pos = fileName.lastIndexOf(".");
			String extension = fileName.substring(pos + 1);
			if (extension != "png" || extension != "jpg") {
				System.out.println("png가 나 jps 형식이 아닙니다");
				resp.sendRedirect(req.getContextPath() + "/fowardreviewwrite?seq=" + sresvSeq + "&rating=" + srating
						+ "&title=" + title + "&content=" + content);
				return;
			}
		}

		Singleton s = Singleton.getInstance();
		BM_MemberDTO memDto = s.memberService.selectOneMember(sloginId);

		ReviewDTO dto = new ReviewDTO(Integer.parseInt(shotelSeq), Integer.parseInt(sroomSeq),
				Integer.parseInt(sresvSeq), memDto.getSeq(), title, content, Double.parseDouble(srating));
		dto.setFileName(fileName);
		dto.setFileRealName(fileRealName);

		boolean tableAddSuccess = s.reviewService.insertReview(dto);
		if (tableAddSuccess) {
			resp.sendRedirect(
					req.getContextPath() + "/fowardmyresvhistory?loginId=" + sloginId + "&nowTime=" + nowTime);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
