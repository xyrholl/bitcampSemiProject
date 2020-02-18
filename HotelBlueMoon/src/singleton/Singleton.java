package singleton;

import java.util.ArrayList;

import db.DBConnection;
import dto.HotelDTO;
import model.jdy.ResvService;
import model.jhj.HotelListService;
import model.jhj.PlaceReadService;
import model.jhj.RoomListService;
import model.lsy.MemberService;
import model.lsy.ReviewService;
import model.sjh.MyPageService;
import model.sjh.QnAService;

public class Singleton {

	private static Singleton s = new Singleton();

	public MemberService memberService = null;
	public MyPageService myPageService = null;
	public QnAService qnaService = null;
	public ReviewService reviewService = null;
	public ResvService resvSerivce = null;
	public HotelListService hotelService = null;
	public PlaceReadService placeRead = null;
	public RoomListService roomListService = null;

	private Singleton() {

		myPageService = new MyPageService();
		qnaService = new QnAService();
		memberService = new MemberService();
		reviewService = new ReviewService();
		resvSerivce = new ResvService();
		DBConnection.initConnection();

	}

	public static Singleton getInstance() {
		return s;
	}

	public String createJson(String guest, String area, String checkin, String checkout) {
		hotelService = new model.jhj.HotelListService();
		String json = hotelService.createJson(guest, area, checkin, checkout);

		return json;
	}
	
	public String getPlace(){
		placeRead = new PlaceReadService();
		String json = placeRead.getPlace();
		
		return json;
	}
	
	public String getRoom(String guest, String checkin, String checkout, String hotelSeq) {
		roomListService = new RoomListService();
		String json = roomListService.createJson(guest, checkin, checkout, hotelSeq);
		
		return json;
	}

}
