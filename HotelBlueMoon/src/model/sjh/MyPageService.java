package model.sjh;

import dto.BM_MemberDTO;
import dto.ResvDTO;

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
	
	public ResvDTO getMyResv(int seq) {
		ResvDTO dto = mypageDao.getMyResv(seq);
		return dto;
	}
	
	public String gethotelName(int seq) {
		String str = mypageDao.gethotelName(seq);
		return str;
	}
	public String getroomName(int seq) {
		String str = mypageDao.getroomName(seq);
		return str;
	}public String getmemName(int seq) {
		String str = mypageDao.getmemName(seq);
		return str;
	}

	public boolean resvCancel(int seq) {
		boolean b = mypageDao.resvCancel(seq);
		return b;
	}

	public int getMyReviewSeq(int resvSeq) {
		int seq = mypageDao.getMyReviewSeq(resvSeq);
		return seq;
	}
}
