package repositories;

import models.Schedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {

    private final SessionFactory sessionFactory;

    public ScheduleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(schedule);
            session.getTransaction().commit();
        }
    }

    @Override
    public Schedule getScheduleById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Schedule.class, id);
        }
    }

    @Override
    public List<Schedule> getSchedulesByStudentId(int studentId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Schedule> query = session.createQuery("from Schedule where student.id=:studentId", Schedule.class);
            query.setParameter("studentId", studentId);
            return query.list();
        }
    }

    @Override
    public List<Schedule> getSchedulesByProfessorId(int professorId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Schedule> query = session.createQuery("from Schedule where professor.id=:professorId", Schedule.class);
            query.setParameter("professorId", professorId);
            return query.list();
        }
    }

    @Override
    public List<Schedule> getSchedulesBySubjectId(int subjectId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Schedule> query = session.createQuery("from Schedule where subject.id=:subjectId", Schedule.class);
            query.setParameter("subjectId", subjectId);
            return query.list();
        }
    }

    @Override
    public List<Schedule> getAllSchedules() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Schedule", Schedule.class).list();
        }
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(schedule);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(schedule);
            session.getTransaction().commit();
        }
    }
}
