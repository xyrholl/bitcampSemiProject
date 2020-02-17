package dto;

/*
CREATE TABLE HOTEL(
SEQ NUMBER(8) PRIMARY KEY,
NAME VARCHAR2(50) NOT NULL,
PLACE VARCHAR2(20) NOT NULL,
ADDR VARCHAR2(100) NOT NULL,
USE_COUNT NUMBER(8) NOT NULL,
RATING NUMBER(3,2) NOT NULL
HOTEL_IMG
);
*/

public class HotelDTO {

	private int seq;
	private String name;
	private String place;
	private String addr;
	private int useCount;
	private double rating;
	private String hotel_img;
	public HotelDTO() {
	}

	

	public HotelDTO(int seq, String name, String place, String addr, int useCount, double rating, String hotel_img) {
		super();
		this.seq = seq;
		this.name = name;
		this.place = place;
		this.addr = addr;
		this.useCount = useCount;
		this.rating = rating;
		this.hotel_img = hotel_img;
	}


	public HotelDTO(int seq, String name, String addr, int useCount, int rating) {
		super();
		this.seq = seq;
		this.name = name;
		this.addr = addr;
		this.rating = rating;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getUseCount() {
		return useCount;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}



	public String getHotel_img() {
		return hotel_img;
	}



	public void setHotel_img(String hotel_img) {
		this.hotel_img = hotel_img;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}
	

}
