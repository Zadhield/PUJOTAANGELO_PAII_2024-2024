package repositories;

import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private final SessionFactory sessionFactory;

    public StudentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addStudent(Student student) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public Student getStudentById(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            transaction.commit();
            return student;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student getStudentByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("from Student where name=:name", Student.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    @Override
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(student);  // Utiliza session.update() en lugar de session.save() para actualizar
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
