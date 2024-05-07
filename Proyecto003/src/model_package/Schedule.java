package model_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import interfaces.IDAOSchedule;

public class Schedule implements IDAOSchedule{

	private int id_subject;
	private int id_alumn;
	private int id_professor;
	private String start_time;
	private String end_time;
	private String day;
	private String table = "Schedule";
	private PreparedStatement ps;

	public Schedule(int id_mat, int id_alumno, int id_profesor, String start_time, String end_time, String day) {
		this.id_subject = id_mat;
		this.id_alumn = id_alumno;
		this.id_professor = id_profesor;
		this.start_time = start_time;
		this.end_time = end_time;
		this.day = day;
	}

	@Override
	public void create(Connection connection, Schedule schedule) throws SQLException {
		ps = connection.prepareStatement("INSERT INTO Schedule (id_alumn, id_professor, id_subject, start_time,end_time, day) VALUES (?,?,?,?,?,?)");
		ps.setInt(1, schedule.getId_alumn());
		ps.setInt(2, schedule.getId_professor());
		ps.setInt(3, schedule.getId_subject());
		ps.setString(4, schedule.getStart_time());
		ps.setString(5, schedule.getEnd_time());
		ps.setString(6, schedule.getDay());
		ps.execute();
		ps.close();
		
	}

	@Override
	public Schedule read(Connection connection, Schedule schedule, int id_schedule) throws SQLException {
	
		return null;
	}

	@Override
	public void update(Connection connection, Schedule schedule, int id_schedule) throws SQLException {
		
	}

	@Override
	public void delete(Connection connection, int id_schedule) throws SQLException {
		
	}

	@Override
	public void createTable(Connection connection) throws SQLException {
	    String query = "CREATE TABLE IF NOT EXISTS Schedule ( " 
	                 + "id_alumn INT,"
	                 + "id_professor INT,"
	                 + "id_subject INT,"
	                 + "start_time VARCHAR(30)," 
	                 + "end_time VARCHAR(30)," 
	                 + "day VARCHAR(30),"
	                 + "FOREIGN KEY (id_alumn) REFERENCES Alumns(id)," 
	                 + "FOREIGN KEY (id_professor) REFERENCES Professors(id),"
	                 + "FOREIGN KEY (id_subject) REFERENCES Subject(id)" + ")";
	    connection.createStatement().executeUpdate(query);
	    System.out.println("Tabla creada o ya existente.");
	}



	public int getId_subject() {
		return id_subject;
	}

	public void setId_subject(int id_subject) {
		this.id_subject = id_subject;
	}

	public int getId_alumn() {
		return id_alumn;
	}

	public void setId_alumn(int id_alumn) {
		this.id_alumn = id_alumn;
	}

	public int getId_professor() {
		return id_professor;
	}

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
		
}
