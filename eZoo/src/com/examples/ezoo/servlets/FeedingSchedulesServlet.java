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
import com.examples.ezoo.model.Schedule;


@WebServlet("/FeedingSchedules")
public class FeedingSchedulesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FeedingSchedulesServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<Schedule> schedules = dao.allSchedules();
		
		request.getSession().setAttribute("schedules", schedules);
		
		request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
		
	}


}
