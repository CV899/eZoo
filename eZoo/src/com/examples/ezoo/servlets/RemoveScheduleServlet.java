package com.examples.ezoo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;


@WebServlet("/RemoveSchedule")
public class RemoveScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RemoveScheduleServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long animalID = Long.parseLong(request.getParameter("animalID"));
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		try {
			
			dao.removeSchedule(animalID);
			request.getSession().setAttribute("message", "Schedule successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			
		} catch(Exception e) {
			
			request.getSession().setAttribute("message", "There was a problem removing the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
		}
		
		response.sendRedirect("animalCare");
	}


}
