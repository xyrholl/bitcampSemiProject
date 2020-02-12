// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   hotelListController.java

package controller.jhj;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/main")
public class mainController extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
    	try {
        req.setCharacterEncoding("utf-8");
        System.out.println(req.getParameter("area"));
        String checkin = URLEncoder.encode(req.getParameter("checkin"), "UTF-8");
        String checkout = URLEncoder.encode(req.getParameter("checkout"), "UTF-8");
        String guest = URLEncoder.encode(req.getParameter("guest"), "UTF-8");
        String area = URLEncoder.encode(req.getParameter("area"), "UTF-8");
        String flag = URLEncoder.encode(req.getParameter("flag"), "UTF-8");
        resp.sendRedirect(req.getContextPath() + "/JSP/hotelList.jsp?checkin=" + checkin + "&checkout=" + checkout
        		+ "&guest=" + guest + "&area=" + area + "&flag=" + flag);
    	}catch(NullPointerException e) {
    		 resp.sendRedirect(req.getContextPath() + "/JSP/hotelList.jsp");
    	}
    }
}
