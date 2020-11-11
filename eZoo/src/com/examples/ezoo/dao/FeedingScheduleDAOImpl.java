/**
 * 
 */
package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.Schedule;

/**
 * @author tahid
 *
 */
public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {

	@Override
	public void addSchedule(Schedule schedule) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULE VALUES (?,?,?,?,?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, schedule.getScheduleID());
			stmt.setString(2, schedule.getFeedingTime());
			stmt.setString(3, schedule.getRecurrence());
			stmt.setString(4, schedule.getFood());
			stmt.setString(5, schedule.getNotes());
			
			success = stmt.executeUpdate();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			// TODO: handle exception in addSchedule()
			//throw new Exception("Insert schedule failed: " + schedule);
		}
	}
		

	@Override
	public void deleteSchedule(Schedule schedule) {

		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		AnimalDAO animalDao = DAOUtilities.getAnimalDao();
		List<Animal> animals = animalDao.getAllAnimals();
		
		// Remove all references to the schedule in animals table
		for (Animal animal : animals) {
			if(animal.getFeedingScheduleID() == schedule.getScheduleID()) {
				try {
					
					connection = DAOUtilities.getConnection();
					String sql = "UPDATE ANIMALS SET feeding_schedule=0 WHERE animalID=?";
					stmt = connection.prepareStatement(sql);
					
					stmt.setLong(1, animal.getAnimalID());
					
					success = stmt.executeUpdate();
					
					if (success == 0) {
						// TODO: handle exception in deleteSchedule()
						//throw new Exception("Deleting schedule " + schedule.getScheduleID() +
						//" for animal " + animal.getAnimalID() + " failed");
					}
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (stmt != null)
							stmt.close();
						if (connection != null)
							connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		// Delete the schedule from the feeding_schedule table
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM feeding_schedule WHERE schedule_id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, schedule.getScheduleID());
			
			success = stmt.executeUpdate();
			
			if (success == 0) {
				// TODO: handle exception in deleteSchedule()
				//throw new Exception("Deleting schedule " + schedule.getScheduleID() +
				//" for animal " + animal.getAnimalID() + " failed");
			}
			
			
		} catch(SQLException e) {
			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Schedule> allSchedules() {
		
		List<Schedule> schedules = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM feeding_schedule";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Schedule s = new Schedule();

				s.setScheduleID(rs.getLong("schedule_id"));
				s.setFeedingTime(rs.getString("feeding_time"));
				s.setRecurrence(rs.getString("recurrence"));
				s.setFood(rs.getString("food"));
				s.setNotes(rs.getString("notes"));

				schedules.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return schedules;
		
	}

	@Override
	public Schedule getSchedule(Animal animal) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Schedule schedule = new Schedule();
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM feeding_schedule WHERE schedule_id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, animal.getFeedingScheduleID());
			
			ResultSet rs = stmt.executeQuery();
			
			schedule.setScheduleID(rs.getLong("schedule_id"));
			schedule.setFeedingTime(rs.getString("feeding_time"));
			schedule.setRecurrence(rs.getString("recurrence"));
			schedule.setFood(rs.getString("food"));
			schedule.setNotes(rs.getString("notes"));
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return schedule;
	}

	@Override
	public void assignSchedule(Schedule schedule, Animal animal) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=? WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, schedule.getScheduleID());
			stmt.setLong(2, animal.getAnimalID());
			
			success = stmt.executeUpdate();
			

			if (success == 0) {
				// TODO: handle exception in deleteSchedule()
				//throw new Exception("Assigning schedule " + schedule.getScheduleID() +
				//" to animal " + animal.getAnimalID() + " failed");
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void removeSchedule(Animal animal) {

		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=0 WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, animal.getAnimalID());
			
			success = stmt.executeUpdate();
			
			if (success == 0) {
				// TODO: handle exception in deleteSchedule()
				//throw new Exception("Deleting schedule for animal "
				//+ animal.getAnimalID() + " failed");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
