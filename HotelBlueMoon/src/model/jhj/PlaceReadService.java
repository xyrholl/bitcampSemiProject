package model.jhj;

import java.util.ArrayList;

public class PlaceReadService {
	
	PlaceReadDAO placeDAO = null; 
	
	public String getPlace(){
		placeDAO = new PlaceReadDAO();
		
		String json = createJson();
		
		return json;
	}
	
	public String createJson() {
		String temp="[";
		
		ArrayList<String> list = placeDAO.getPlaceList();
		
		for(int i=0 ; i<list.size() ; i++) {
			temp+="{";
			
			temp+="\"name\":\""+list.get(i)+"\"";			
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
