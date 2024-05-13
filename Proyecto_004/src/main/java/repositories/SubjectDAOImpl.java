package repositories;

import models.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    private final SessionFactory sessionFactory;

    public SubjectDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSubject(Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(subject);
            session.getTransaction().commit();
        }
    }

    @Override
    public Subject getSubjectById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Subject.class, id);
        }
    }

    @Override
    public Subject getSubjectByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Subject> query = session.createQuery("from Subject where name=:name", Subject.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Subject> getAllSubjects() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Subject", Subject.class).list();
        }
    }

    @Override
    public void updateSubject(Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(subject);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteSubject(Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(subject);
            session.getTransaction().commit();
        }
    }
}
