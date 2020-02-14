package dto;

/*
CREATE TABLE ROOM(
SEQ NUMBER(8) PRIMARY KEY,
NAME VARCHAR2(100) NOT NULL,
HotelSEQ NUMBER(8) NOT NULL,
PRICE NUMBER(10) NOT NULL,
MAX_GUEST NUMBER(2) NOT NULL
);
*/

public class RoomDTO {

	private int seq;
	private String name;
	private int hotelSeq;
	private int price;
	private int max_guest;

	public RoomDTO() {
	}

	public RoomDTO(int seq, String name, int hotelSeq, int price, int max_guest) {
		super();
		this.seq = seq;
		this.name = name;
		this.hotelSeq = hotelSeq;
		this.price = price;
		this.max_guest = max_guest;
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

	public int getHotelSeq() {
		return hotelSeq;
	}

	public void setHotelSeq(int hotelSeq) {
		this.hotelSeq = hotelSeq;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMax_guest() {
		return max_guest;
	}

	public void setMax_guest(int max_guest) {
		this.max_guest = max_guest;
	}

	@Override
	public String toString() {
		return "RoomDTO [seq=" + seq + ", name=" + name + ", hotelSeq=" + hotelSeq + ", price=" + price + ", max_guest="
				+ max_guest + "]";
	}

}
