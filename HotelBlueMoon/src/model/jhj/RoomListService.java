package model.jhj;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dto.ResvDTO;



public class RoomListService {
	
	
RoomListDAO dao = new RoomListDAO();
	
	public ArrayList<ResvDTO> getRoomInfo(String guest, String checkin, String checkout, String hotelSeq) {
		
		int timeCount = (int)betweenTime(checkin, checkout);
		
		System.out.println(timeCount);
		
		ArrayList<ResvDTO> list = dao.getRoomList(guest, checkin, checkout, hotelSeq, timeCount);
		
		return list;
	}
	
	public long betweenTime(String firstDate, String secondDate) {
		try{
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	        Date first = format.parse(firstDate);
	        Date second = format.parse(secondDate);

	        long calDate = first.getTime() - second.getTime(); 

	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        calDateDays = Math.abs(calDateDays);
	        
	        System.out.println("시간차: "+calDateDays);
	        
	        return calDateDays;
	        
	        }
	        catch(ParseException e)
	        {

	        }
		
		return 0;
	}
	
	public String createJson(String guest, String checkin, String checkout, String hotelSeq) {
		String temp="[";
		
		ArrayList<ResvDTO> list = getRoomInfo(guest, checkin, checkout, hotelSeq);
		
		for(int i=0 ; i<list.size() ; i++) {
			temp+="{";
			
			temp+="\"seq\":\""+list.get(i).getSeq()+"\",";
			temp+="\"name\":\""+list.get(i).getRoomName()+"\",";
			temp+="\"hotelSeq\":\""+list.get(i).getHotelSeq()+"\",";
			temp+="\"guest\":\""+list.get(i).getRoomMax_guest()+"\",";
			temp+="\"image\":\""+list.get(i).getHotelAddr()+"\"";
			
			if(i == list.size()-1) {
				break;
			}
			temp+="},";
		}
		temp += "}]";
		
		System.out.println(temp);
		
		return temp;
	}
	
}
