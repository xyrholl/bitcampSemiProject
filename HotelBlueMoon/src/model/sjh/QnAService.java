package model.sjh;

import java.util.List;

import dto.QnADTO;

public class QnAService {
	QnADao qnaDao = new QnADao();
	/*
	public List<QnADTO> getQnAList(){
		List<QnADTO> list = qnaDao.getQnAList();
		return list;
	}
	*/
	public QnADTO getQnADetail(int seq) {
		QnADTO dto = qnaDao.getQnADetail(seq);
		return dto;
	}
	
	
	public List<QnADTO> getQnAPagingList(String choice, String searchWord, int page){
		List<QnADTO> list = qnaDao.getQnAPagingList(choice, searchWord, page);
		return list;
	}
	
	public int getAllQnA(String choice, String searchWord) {
		int len = qnaDao.getAllQnA(choice, searchWord);
		return len;
	}
	
	public boolean QnAWrite(int memberseq, String title, String content) {
		boolean b = qnaDao.qnaWrite(memberseq, title, content);
		return b;
	}
	public boolean qnaUpdate(int seq, String title, String content) {
		boolean b = qnaDao.qnaUpdate(seq, title, content);
		return b;
	}
	
	public void qnaReadCount(int seq) {
		qnaDao.qnaReadCount(seq);
	}
	
	
	public boolean qnaDelete(int seq) {
		boolean b = qnaDao.qnaDelete(seq);
		return b;
	}

	public int getMemberseq(String loginId) {
		int seq = qnaDao.getMemberseq(loginId);
		return seq; 
	}
	
	public boolean qnaComment(int seq, String loginId, String title, String content) {
		boolean b = qnaDao.qnaComment(seq, loginId, title, content);
		return b;
	}
	
}
