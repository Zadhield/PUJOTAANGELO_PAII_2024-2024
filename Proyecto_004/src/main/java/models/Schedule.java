package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule {
    
    @Id
   
    private int id;
    
    @ManyToOne
    @JoinColumn(name="id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name="id_professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name="id_subject")
    private Subject subject;

    @Column(name="start_time")
    private String startTime;

    @Column(name="end_time")
    private String endTime;

    @Column(name="day")
    private String day;
    
    public Schedule() {
        
    }

    public Schedule( int id,Student student, Professor professor, Subject subject, String startTime, String endTime, String day) {
        super();
        this.id = id;
        this.student = student;
        this.professor = professor;
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
