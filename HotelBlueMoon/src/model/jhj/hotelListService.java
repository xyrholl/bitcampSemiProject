package model.jhj;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dto.HotelDTO;

public class hotelListService {
	
	hotelListDAO dao = new hotelListDAO();
	
	public ArrayList<HotelDTO> getHotelInfo(String guest, String area, String checkin, String checkout) {
		
		int timeCount = (int)betweenTime(checkin, checkout);
		
		System.out.println(timeCount);
		
		ArrayList<String> hotelNameList = dao.getHotelTimeList(checkin, checkout, area, timeCount);
		
		for(int i = 0 ; i < hotelNameList.size() ; i++) {
			System.out.println(hotelNameList.get(i));
		}
		
		ArrayList<HotelDTO> list = dao.getHotelList(hotelNameList, guest, area);
		
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
	
	public String createJson(String guest, String area, String checkin, String checkout) {
		String temp="[";
		
		ArrayList<HotelDTO> list = getHotelInfo(guest, area, checkin, checkout);
		
		for(int i=0 ; i<list.size() ; i++) {
			temp+="{";
			
			temp+="\"seq\":\""+list.get(i).getSeq()+"\",";
			temp+="\"name\":\""+list.get(i).getName()+"\",";
			temp+="\"addr\":\""+list.get(i).getAddr()+"\",";
			temp+="\"count\":\""+list.get(i).getUseCount()+"\",";
			temp+="\"rating\":\""+list.get(i).getRating()+"\"";
						
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
