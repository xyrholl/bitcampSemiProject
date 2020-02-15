package controller.lsy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import singleton.Singleton;

@WebServlet("/formImageFile")
public class ReviewImagefileUploadController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = req.getSession().getServletContext().getRealPath("/UPload");

		int maxSize = 1024 * 1024 * 10;
		String encoding = "UTF-8";

		MultipartRequest multipartReq = new MultipartRequest(req, saveDirectory, maxSize, encoding,
				new DefaultFileRenamePolicy());

		String fileName = multipartReq.getOriginalFileName("imageFile");
		String fileRealName = multipartReq.getFilesystemName("imageFile");

		Singleton s = Singleton.getInstance();
		boolean b = s.reviewService.imageFileUpload(fileName, fileRealName);
		if (b) {
			System.out.println("파일 저장완료");
		} else {
			System.out.println("파일저장 실패");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
