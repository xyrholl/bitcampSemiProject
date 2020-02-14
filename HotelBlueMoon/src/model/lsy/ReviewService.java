package model.lsy;

import java.util.List;

import dto.ResvDTO;
import dto.ReviewDTO;

public class ReviewService {

	ReviewDAO reviewDao = new ReviewDAO();

	public ReviewDTO selectOne(int seq) {
		return reviewDao.selectOne(seq);
	}

	public List<ReviewDTO> reviewPageList() {
		return reviewDao.reviewList();
	}

	public List<ReviewDTO> reviewPageList(int selectIndex, String searchText) {
		return reviewDao.reviewList(selectIndex, searchText);
	}

	public boolean updateWritePage(String seq, String rating, String title, String content) {
		int count = reviewDao.updateOneWrite(Integer.parseInt(seq), rating, title, content);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public List<ResvDTO> resvList(String loginId) {
		return reviewDao.writeReviewCheck(loginId);
	}

	public List<ResvDTO> resvList(String loginId, int selectIndex, String searchText) {
		return reviewDao.writeReviewCheck(loginId, selectIndex, searchText);
	}

}
