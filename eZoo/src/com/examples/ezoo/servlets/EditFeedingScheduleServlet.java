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
 * Servlet implementation class EditFeedingScheduleServlet
 */
@WebServlet("/EditFeedingSchedule")
public class EditFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditFeedingScheduleServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long ID = Long.parseLong(request.getParameter("ID"));
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		Schedule schedule = dao.getSchedule(ID);
		
		request.getSession().setAttribute("schedule", schedule);
		
		request.getRequestDispatcher("editSchedule.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long ID = Long.parseLong(request.getParameter("ID"));
		String feedingTime = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		Schedule schedule = new Schedule(ID, feedingTime, recurrence, food, notes);
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		try {
			
			dao.editSchedule(schedule);
			request.getSession().setAttribute("message", "Schedule successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("FeedingSchedules");
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			request.getSession().setAttribute("message", "There was a problem updating the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("EditFeedingSchedule?ID=" + ID).forward(request, response);
		}
	}

}
