package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.Schedule;

/**
 * Servlet implementation class AssignFeedingScheduleServlet
 */
@WebServlet("/AssignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AssignFeedingScheduleServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<Schedule> schedules = dao.allSchedules();
		
		request.getSession().setAttribute("schedules", schedules);
		long animalID = Long.parseLong(request.getParameter("animalID"));
		String animalName = request.getParameter("animalName");
		
		request.getSession().setAttribute("animalID", animalID);
		request.getSession().setAttribute("animalName", animalName);
		
		request.getRequestDispatcher("assignSchedule.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
