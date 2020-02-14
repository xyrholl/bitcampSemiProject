package singleton;

import java.util.ArrayList;

import db.DBConnection;
import dto.HotelDTO;
import model.jdy.ResvService;
import model.jhj.hotelListService;
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
	public hotelListService hotelService = null;

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

	public ArrayList<HotelDTO> getHotelInfo(String guest, String area, String checkin, String checkout) {
		hotelService = new model.jhj.hotelListService();
		ArrayList<HotelDTO> list = hotelService.getHotelInfo(guest, area, checkin, checkout);

		return list;
	}

	public String createJson(String guest, String area, String checkin, String checkout) {
		hotelService = new model.jhj.hotelListService();
		String json = hotelService.createJson(guest, area, checkin, checkout);

		return json;
	}

}
