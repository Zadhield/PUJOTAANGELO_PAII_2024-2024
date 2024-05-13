package repositories;

import models.Schedule;

import java.util.List;

public interface ScheduleDAO {
    void addSchedule(Schedule schedule);
    Schedule getScheduleById(int id);
    List<Schedule> getSchedulesByStudentId(int studentId);
    List<Schedule> getSchedulesByProfessorId(int professorId);
    List<Schedule> getSchedulesBySubjectId(int subjectId);
    List<Schedule> getAllSchedules();
    void updateSchedule(Schedule schedule);
    void deleteSchedule(Schedule schedule);
}
