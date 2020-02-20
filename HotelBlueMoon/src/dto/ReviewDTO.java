package dto;

/*
CREATE TABLE REVIEW(
SEQ NUMBER(8) PRIMARY KEY,
HotelSEQ NUMBER(8) NOT NULL,
RoomSEQ NUMBER(8) NOT NULL,
ResvSEQ NUMBER(8) NOT NULL,
MemberSEQ NUMBER(8) NOT NULL,
TITLE VARCHAR2(100) NOT NULL,
CONTENT VARCHAR2(500) NOT NULL,
RATING NUMBER(3,2) NOT NULL,
WRITEDATE DATE NOT NULL,
DEL NUMBER(1) NOT NULL
);
*/

public class ReviewDTO {

	private int seq;
	private int hotleSeq;
	private int roomSeq;
	private int resvSeq;
	private int memberSeq;

	private String title;
	private String content;
	private Double rating;
	private String writeDate;
	private int del;
	private int rowNum;
	private String fileName;
	private String fileRealName;

	// HotelDTO
	private String hotelName;
	private Double hotelRating;

	// RoomDTO
	private String roomName;
	private String roomImg;

	// ResvDTO
	private String checkInDate;
	private String checkOutDate;
	private int current_guest;

	// MemberDTO
	private String memberId;

	public ReviewDTO() {
	}

	public ReviewDTO(int seq, String title, String content, Double rating, String hotelName) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.hotelName = hotelName;
	}

	public ReviewDTO(int hotleSeq, int roomSeq, int resvSeq, int memberSeq, String title, String content,
			Double rating) {
		super();
		this.hotleSeq = hotleSeq;
		this.roomSeq = roomSeq;
		this.resvSeq = resvSeq;
		this.memberSeq = memberSeq;
		this.title = title;
		this.content = content;
		this.rating = rating;
	}

	public ReviewDTO(String hotelName, Double hotelrating, String roomName, String checkInDate, String checkOutDate,
			int current_guest, String memberId) {
		super();
		this.hotelName = hotelName;
		this.hotelRating = hotelrating;
		this.roomName = roomName;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.current_guest = current_guest;
		this.memberId = memberId;
	}

	public String getRoomImg() {
		return roomImg;
	}

	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getHotleSeq() {
		return hotleSeq;
	}

	public void setHotleSeq(int hotleSeq) {
		this.hotleSeq = hotleSeq;
	}

	public int getRoomSeq() {
		return roomSeq;
	}

	public void setRoomSeq(int roomSeq) {
		this.roomSeq = roomSeq;
	}

	public int getResvSeq() {
		return resvSeq;
	}

	public void setResvSeq(int resvSeq) {
		this.resvSeq = resvSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getHotelRating() {
		return hotelRating;
	}

	public void setHotelRating(Double hotelRating) {
		this.hotelRating = hotelRating;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getCurrent_guest() {
		return current_guest;
	}

	public void setCurrent_guest(int current_guest) {
		this.current_guest = current_guest;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "ReviewDTO [seq=" + seq + ", hotleSeq=" + hotleSeq + ", roomSeq=" + roomSeq + ", resvSeq=" + resvSeq
				+ ", memberSeq=" + memberSeq + ", title=" + title + ", content=" + content + ", rating=" + rating
				+ ", writeDate=" + writeDate + ", del=" + del + ", hotelName=" + hotelName + ", hotelRating="
				+ hotelRating + ", roomName=" + roomName + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + ", current_guest=" + current_guest + ", memberId=" + memberId + "]";
	}

}
