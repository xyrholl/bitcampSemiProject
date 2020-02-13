package model.lsy;

import dto.BM_MemberDTO;

public class MemberService {

	MemberDAO memberDAO = new MemberDAO();

	public BM_MemberDTO selectOneMember(String id) {
		BM_MemberDTO dto = memberDAO.getMyPage(id);
		return dto;
	}

	public boolean idCheck(String id) {
		BM_MemberDTO dto = memberDAO.selectOneMember(id);
		if (dto == null) {
			return false;
		} else {
			return true;
		}
	}

	public String login(String id, String pw) {
		String sessionSaveId = "";
		BM_MemberDTO bm_MemberDTO = memberDAO.loginPwCheck(id, pw);
		if (bm_MemberDTO != null) {
			sessionSaveId = bm_MemberDTO.getId();
		}
		return sessionSaveId;
	}

	public boolean registration(BM_MemberDTO dto) {
		int count = memberDAO.userRegistration(dto);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

}
