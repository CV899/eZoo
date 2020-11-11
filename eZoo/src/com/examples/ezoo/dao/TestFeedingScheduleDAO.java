package com.examples.ezoo.dao;

import java.util.List;
import java.util.*;
import java.sql.*;
import com.examples.ezoo.model.Schedule;

public class TestFeedingScheduleDAO { // success!

	 public static void main(String[] args){
		    FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
		    List<Schedule> list = dao.allSchedules();
		    for (int i = 0; i < list.size(); i++){
		      Schedule f = list.get(i);
		      System.out.println(f);
		    }
	 }
}
