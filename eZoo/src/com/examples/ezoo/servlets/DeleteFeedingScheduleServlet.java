package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Schedule;

/**
 * Servlet implementation class DeleteFeedingScheduleServlet
 */
@WebServlet("/DeleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteFeedingScheduleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long scheduleID = Long.parseLong(request.getParameter("scheduleID"));
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		Schedule schedule = dao.getSchedule(scheduleID);
		
		try {
			
			dao.deleteSchedule(schedule);
			request.getSession().setAttribute("message", "Schedule successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("FeedingSchedules");
			
		} catch(Exception e) {
			
			request.getSession().setAttribute("message", "There was a problem deleting the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			response.sendRedirect("FeedingSchedules");
			
		}
		
	}
	
}
