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

@WebServlet("/AddSchedule")
public class AddScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddScheduleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("addSchedule.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long scheduleID = Long.parseLong(request.getParameter("id"));
		String feedingTime = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		Schedule schedule = new Schedule(scheduleID, feedingTime, recurrence, food, notes);
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		try {
			
			dao.addSchedule(schedule);
			request.getSession().setAttribute("message", "Schedule successfully created");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("FeedingSchedules");
			
		} catch(Exception e) {
			
			e.printStackTrace();
			request.getSession().setAttribute("message", "There was a problem creating the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("animalCare").forward(request, response);
			
		}
		
		
	}

}
