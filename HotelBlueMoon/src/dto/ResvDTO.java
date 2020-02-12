package dto;

/*
CREATE TABLE RESV(
SEQ NUMBER(8) PRIMARY KEY,
HotelSEQ NUMBER(8) NOT NULL,
MemberSEQ NUMBER(8) NOT NULL,
RoomSEQ NUMBER(8) NOT NULL,
CheckIn VARCHAR2(20) NOT NULL,
CheckOut VARCHAR2(20) NOT NULL,
RESVDATE DATE NOT NULL,
TOTALPRICE NUMBER(8) NOT NULL,
CURRENT_GUEST NUMBER(2) NOT NULL,
CANCEL NUMBER(1) NOT NULL,
REVIEWIS NUMBER(1) NOT NULL
);
 */
public class ResvDTO {
	private int seq;
	private int memberSeq;
	private int roomSeq;
	private int hotelSeq;

	private String checkIn;
	private String checkOut;
	private String resvDate;
	private int totalPrice;
	private int cancel;
	private int current_guest;
	private int reviewIs;

	// HOTELDTO
	private String hotelName;
	private String hotelPlace;
	private String hotelAddr;
	private int hotelUseCount;
	private double hotelRating;

	// roomDTO
	private String roomName;
	private int roomPrice;
	private int roomMax_guest;

	// memDTO
	private String id;
	private String pwd;
	private String memName;
	private String phoneNum;
	private String email;

	public int getReviewIs() {
		return reviewIs;
	}

	public void setReviewIs(int reviewIs) {
		this.reviewIs = reviewIs;
	}

	public ResvDTO() {
	}

	public ResvDTO(int seq, int memberSeq, int roomSeq, int hotelSeq, String checkIn, String checkOut, String resvDate,
			int totalPrice, int cancel, int current_guest) {
		super();
		this.seq = seq;
		this.memberSeq = memberSeq;
		this.roomSeq = roomSeq;
		this.hotelSeq = hotelSeq;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.resvDate = resvDate;
		this.totalPrice = totalPrice;
		this.cancel = cancel;
		this.current_guest = current_guest;
	}

	public ResvDTO(int seq, int memberSeq, int roomSeq, int hotelSeq, String checkIn, String checkOut, String resvDate,
			int totalPrice, int cancel, int current_guest, String hotelName, String hotelPlace, String hotelAddr,
			int hotelUseCount, int hotelRating, String roomName, int roomPrice, int roomMax_guest, String id,
			String pwd, String memName, String phoneNum, String email) {
		super();
		this.seq = seq;
		this.memberSeq = memberSeq;
		this.roomSeq = roomSeq;
		this.hotelSeq = hotelSeq;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.resvDate = resvDate;
		this.totalPrice = totalPrice;
		this.cancel = cancel;
		this.current_guest = current_guest;
		this.hotelName = hotelName;
		this.hotelPlace = hotelPlace;
		this.hotelAddr = hotelAddr;
		this.hotelUseCount = hotelUseCount;
		this.hotelRating = hotelRating;
		this.roomName = roomName;
		this.roomPrice = roomPrice;
		this.roomMax_guest = roomMax_guest;
		this.id = id;
		this.pwd = pwd;
		this.memName = memName;
		this.phoneNum = phoneNum;
		this.email = email;
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

	public int getRoomSeq() {
		return roomSeq;
	}

	public void setRoomSeq(int roomSeq) {
		this.roomSeq = roomSeq;
	}

	public int getHotelSeq() {
		return hotelSeq;
	}

	public void setHotelSeq(int hotelSeq) {
		this.hotelSeq = hotelSeq;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getResvDate() {
		return resvDate;
	}

	public void setResvDate(String resvDate) {
		this.resvDate = resvDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public int getCurrent_guest() {
		return current_guest;
	}

	public void setCurrent_guest(int current_guest) {
		this.current_guest = current_guest;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelPlace() {
		return hotelPlace;
	}

	public void setHotelPlace(String hotelPlace) {
		this.hotelPlace = hotelPlace;
	}

	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}

	public int getHotelUseCount() {
		return hotelUseCount;
	}

	public void setHotelUseCount(int hotelUseCount) {
		this.hotelUseCount = hotelUseCount;
	}

	public double getHotelRating() {
		return hotelRating;
	}

	public void setHotelRating(double d) {
		this.hotelRating = d;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getRoomMax_guest() {
		return roomMax_guest;
	}

	public void setRoomMax_guest(int roomMax_guest) {
		this.roomMax_guest = roomMax_guest;
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

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
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
		return "ResvDTO [seq=" + seq + ", memberSeq=" + memberSeq + ", roomSeq=" + roomSeq + ", hotelSeq=" + hotelSeq
				+ ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", resvDate=" + resvDate + ", totalPrice="
				+ totalPrice + ", cancel=" + cancel + ", current_guest=" + current_guest + ", hotelName=" + hotelName
				+ ", hotelPlace=" + hotelPlace + ", hotelAddr=" + hotelAddr + ", hotelUseCount=" + hotelUseCount
				+ ", hotelRating=" + hotelRating + ", roomName=" + roomName + ", roomPrice=" + roomPrice
				+ ", roomMax_guest=" + roomMax_guest + ", id=" + id + ", pwd=" + pwd + ", memName=" + memName
				+ ", phoneNum=" + phoneNum + ", email=" + email + "]";
	}

}
