package repositories;

import models.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {

    private final SessionFactory sessionFactory;

    public ProfessorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProfessor(Professor professor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(professor);
            session.getTransaction().commit();
        }
    }

    @Override
    public Professor getProfessorById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Professor.class, id);
        }
    }

    @Override
    public Professor getProfessorByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Professor> query = session.createQuery("from Professor where name=:name", Professor.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Professor> getAllProfessors() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Professor", Professor.class).list();
        }
    }

    @Override
    public void updateProfessor(Professor professor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(professor);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteProfessor(Professor professor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(professor);
            session.getTransaction().commit();
        }
    }
}
