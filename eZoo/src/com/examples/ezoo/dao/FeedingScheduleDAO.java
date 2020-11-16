package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.Schedule;

public interface FeedingScheduleDAO {
	
	void addSchedule(Schedule schedule);
	
	void deleteSchedule(Schedule schedule);
	
	List<Schedule> allSchedules();
	
	Schedule getSchedule(Long ID);
	
	void removeSchedule(Long animalID) throws Exception;

	void assignSchedule(Long scheduleID, Long animalID) throws Exception;
	
	void editSchedule(Schedule schedule) throws Exception;
	
}
