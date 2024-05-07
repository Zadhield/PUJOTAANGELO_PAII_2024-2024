package interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import model_package.Schedule;

public interface IDAOSchedule {
	
	public void create(Connection connection, Schedule schedule) throws SQLException;

	public Schedule read(Connection connection, Schedule schedule, int id_schedule) throws SQLException;

	public void update(Connection connection,  Schedule schedule, int id_schedule)throws SQLException;

	public void delete(Connection connection, int id_schedule)throws SQLException;
	
	public void createTable(Connection connection) throws SQLException;

}
