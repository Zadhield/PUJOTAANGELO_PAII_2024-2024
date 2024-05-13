package ec.edu.uce.Proyecto_004;

import models.Student;
import models.Professor;
import models.Subject;
import models.Schedule;
import repositories.ProfessorDAO;
import repositories.ProfessorDAOImpl;
import repositories.ScheduleDAO;
import repositories.ScheduleDAOImpl;
import repositories.StudentDAO;
import repositories.StudentDAOImpl;
import repositories.SubjectDAO;
import repositories.SubjectDAOImpl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.EventQueue;
import java.util.Date;
/**
 * @ Angelo Pujota
 * @ CRUD con maven e Hibernate
 */

public class App 
{
	public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        StudentDAO studentRepository = new StudentDAOImpl(sessionFactory);
        ProfessorDAO professorRepository = new ProfessorDAOImpl(sessionFactory);
        SubjectDAO subjectRepository = new SubjectDAOImpl(sessionFactory);
        ScheduleDAO scheduleRepository = new ScheduleDAOImpl(sessionFactory);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame(studentRepository, professorRepository, subjectRepository, scheduleRepository);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    }
