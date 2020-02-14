package dto;

/*
CREATE TABLE BM_MEMBER(
SEQ NUMBER(8) PRIMARY KEY,
ID VARCHAR2(50) NOT NULL,
PWD VARCHAR2(50) NOT NULL,
NAME VARCHAR2(20) NOT NULL,
PHONENUM VARCHAR2(20) NOT NULL,
EMAIL VARCHAR2(30) NOT NULL,
AUTH NUMBER(1) NOT NULL,
DEL NUMBER(1) NOT NULL
);
*/

public class BM_MemberDTO {

	private int seq;
	private String id;
	private String pwd;
	private String name;
	private String phoneNum;
	private String email;

	public BM_MemberDTO() {
	}

	public BM_MemberDTO(String id, String pwd, String name, String phoneNum, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public BM_MemberDTO(int seq, String id, String pwd, String name, String phoneNum, String email) {
		super();
		this.seq = seq;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "BM_MemberDTO [seq=" + seq + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", phoneNum=" + phoneNum
				+ ", email=" + email + "]";
	}

}
