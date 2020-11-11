package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.Schedule;

public interface FeedingScheduleDAO {
	
	void addSchedule(Schedule schedule);
	
	void deleteSchedule(Schedule schedule);
	
	List<Schedule> allSchedules();
	
	Schedule getSchedule(Animal animal);
	
	void assignSchedule(Schedule schedule, Animal animal);
	
	void removeSchedule(Animal animal);
	
}
