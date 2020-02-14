package model.sjh;

import dto.BM_MemberDTO;

public class MyPageService {
	
	MyPageDao mypageDao = new MyPageDao();
	
	public BM_MemberDTO getMyPage(String loginId) {
		BM_MemberDTO dto = mypageDao.getMyPage(loginId);
		return dto;
	}
	
	public boolean mypageUpdate(String loginId, String pwd, String phoneNum, String email) {
		boolean b = mypageDao.myPageUpdate(loginId, pwd, phoneNum, email);
		return b;
	}
	
	public int getMyReviewCount(String loginId) {
		int rcount = mypageDao.getMyReviewCount(loginId);
		return rcount;
	}
	
	public int getMyQnACount(String loginId) {
		int qcount = mypageDao.getMyQnACount(loginId);
		return qcount;
	}
	
	public int getMyResvCount(String loginId) {
		int rsvcount= mypageDao.getMyResvCount(loginId);
		return rsvcount;
	}
	
	public boolean checkPassword(String loginId, String pwd) {
		boolean b = mypageDao.checkPassword(loginId, pwd);
		return b;
	}
			
}
