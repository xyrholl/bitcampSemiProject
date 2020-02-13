package model.lsy;

import java.util.List;

import dto.ResvDTO;
import dto.ReviewDTO;

public class ReviewService {

	ReviewDAO reviewDao = new ReviewDAO();

	public ReviewDTO selectOne(int seq) {
		return reviewDao.ReviewSelectOne(seq);
	}

	public List<ReviewDTO> reviewPageList() {
		return reviewDao.reviewList();
	}

	public List<ReviewDTO> reviewSearchPageList(int selectIndex, String searchText) {
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

	public List<ResvDTO> resvList(String loginId, int selectIndex, String hotelName, String place) {
		return reviewDao.writeReviewCheck(loginId, selectIndex, hotelName, place);
	}

	public ResvDTO ResvSelectOne(int seq) {
		return reviewDao.ResvSelectOne(seq);
	}

	public boolean insertReview(ReviewDTO dto) {
		int count = reviewDao.insertReview(dto);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

}
