package dto;

/*
CREATE TABLE QNA(
SEQ NUMBER(8) PRIMARY KEY,
MemberSEQ NUMBER(8) NOT NULL,
REF NUMBER(2) NOT NULL,
STEP NUMBER(2) NOT NULL,
DEPTH NUMBER(2) NOT NULL,
TITLE VARCHAR2(100) NOT NULL,
CONTENT VARCHAR2(1000) NOT NULL,
WRITEDATE DATE NOT NULL,
DEL NUMBER(1) NOT NULL,
ReadCount NUMBER(8) NOT NULL
);
*/

public class QnADTO {
	
	private int seq;
	private int memberSeq;
	private String memberId;	// 0206 추가
	
	
	private int ref;
	private int step;
	private int depth;
	
	private String title;
	private String content;
	private String writeDate;
	private int del;
	private int readcount;
	
	public QnADTO() {
	}

	
	
	public QnADTO(int seq, String title, String content) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
	}

	public QnADTO(int seq, int memberSeq, String memberId, int ref, int step, int depth, String title, String content,
			String writeDate, int del, int readcount) {
		super();
		this.seq = seq;
		this.memberSeq = memberSeq;
		this.memberId = memberId;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.del = del;
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		return "QnADTO [seq=" + seq + ", memberSeq=" + memberSeq + ", memberId=" + memberId + ", ref=" + ref + ", step="
				+ step + ", depth=" + depth + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", del=" + del + ", readcount=" + readcount + "]";
	}

	public int getSeq() {
		return seq;
	}



	public void setSeq(int seq) {
		this.seq = seq;
	}



	public int getMemberSeq() {
		return memberSeq;
	}



	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public int getRef() {
		return ref;
	}



	public void setRef(int ref) {
		this.ref = ref;
	}



	public int getStep() {
		return step;
	}



	public void setStep(int step) {
		this.step = step;
	}



	public int getDepth() {
		return depth;
	}



	public void setDepth(int depth) {
		this.depth = depth;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getWriteDate() {
		return writeDate;
	}



	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}



	public int getDel() {
		return del;
	}



	public void setDel(int del) {
		this.del = del;
	}



	public int getReadcount() {
		return readcount;
	}



	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	


}
