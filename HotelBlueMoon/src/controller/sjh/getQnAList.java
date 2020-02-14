package controller.sjh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QnADTO;
import singleton.Singleton;

@WebServlet("/qnafoward")
public class getQnAList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}
	public void processFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("--------------------------------getQnAList입니다");
		  String choice = req.getParameter("choice");
		  String searchWord = req.getParameter("searchWord"); 
		  int pageNumber = 0;
		  String spageNumber = req.getParameter("pageNumber");
		  
		  System.out.println("choice: "+choice + " " + "searchWord: "+searchWord);
		  
		  if(spageNumber != null && !spageNumber.equals("")){
				 pageNumber = Integer.parseInt(spageNumber); 
			}

			if(choice == null || choice.equals("")){
				choice = "sel";
			}
			if(choice.equals("sel")){
				searchWord="";
			}
			if(searchWord==null || searchWord.equals("")){
				searchWord="";
				choice="sel";
			}
		  
		  System.out.println("QNA_JAVA = choice: "+choice + " " + "searchWord: "+searchWord + "  pageNumber: " + pageNumber);
		
		Singleton s = Singleton.getInstance();
		List<QnADTO> list = new ArrayList<QnADTO>();
		
		list = s.qnaService.getQnAPagingList(choice, searchWord, pageNumber);
		
		  int totalQnA = s.qnaService.getAllQnA(choice, searchWord);
		  int qnaPage =  totalQnA/5;
		  if( totalQnA % 5 > 0){
		  	qnaPage = qnaPage + 1;
		  }
		
		req.setAttribute("list", list);
		req.setAttribute("qnaPage", qnaPage);
		req.setAttribute("choice", choice);
		req.setAttribute("searchWord", searchWord);
		req.setAttribute("pageNumber", pageNumber);
		
		forward("/JSP/qna.jsp", req, resp);
		
		
		
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

}
