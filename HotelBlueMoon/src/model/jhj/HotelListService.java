package model.jhj;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dto.HotelDTO;

public class HotelListService {
	
	HotelListDAO dao = new HotelListDAO();
	
	public ArrayList<HotelDTO> getHotelInfo(String guest, String area, String checkin, String checkout) {
		
		int timeCount = (int)betweenTime(checkin, checkout);
		
		System.out.println(timeCount);
		
		ArrayList<HotelDTO> list = dao.getHotelList(guest, area, checkin, checkout, timeCount);
		
		return list;
	}
	
	public long betweenTime(String firstDate, String secondDate) {
	      try {
	         String sd1 = firstDate.substring(0, 4);

	         String sd2 = firstDate.substring(4, 6);

	         String sd3 = firstDate.substring(6, 8);

	         String ed1 = secondDate.substring(0, 4);

	         String ed2 = secondDate.substring(4, 6);

	         String ed3 = secondDate.substring(6, 8);

	         firstDate = sd1 + "-" + sd2 + "-" + sd3;

	         secondDate = ed1 + "-" + ed2 + "-" + ed3;

	         SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

	         Date sDate = fm.parse(firstDate);

	         Date eDate = fm.parse(secondDate);

	         long diff = eDate.getTime() - sDate.getTime();

	         long diffDays = diff / (24 * 60 * 60 * 1000);

	         long difMonth = (diffDays + 1) / 30; // 총개월수 ( 대략 30으로 나눴을때 나오는 개월수 )

	         long chkNum = 0;

	         int j = 0;

	         // 개월수 체크 ( 시작한날짜월부터 위에서 대충 계산한 개월수까지 )

	         // 각 월별로 해당하는 월수에 맞게 더해줌

	         for (int i = Integer.parseInt(sd2); j < difMonth; i++) {

	            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {

	               chkNum += 31;

	            } else if (i == 4 || i == 6 || i == 9 || i == 11) {

	               chkNum += 30;

	            }

	            if (i == 2) {

	               // 윤달체크

	               if (((Integer.parseInt(sd2)) % 400) == 0) {

	                  chkNum += 29;

	               } else {

	                  chkNum += 28;

	               }

	            }

	            j++;

	            if (i > 12) {
	               i = 1;
	               j = j - 1;
	            }

	         }

	         long allMonth = (chkNum + 1) / 30;

	         if (diffDays < chkNum) {

	            allMonth = allMonth - 1;

	         }

	         System.out.println("날짜차이 =" + diffDays);

	         System.out.println("총 차이  =" + chkNum);

	         System.out.println("개월수 =" + difMonth);

	         System.out.println("진짜개월수 =" + allMonth);
	         
	         return diffDays;

	      } catch (Exception e) {
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
			temp+="\"rating\":\""+list.get(i).getRating()+"\",";
			temp+="\"hotelImg\":\""+list.get(i).getHotel_img()+"\"";
			
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
