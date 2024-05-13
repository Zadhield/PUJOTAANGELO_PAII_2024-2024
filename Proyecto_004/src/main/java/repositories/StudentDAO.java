package repositories;

import models.Student;

import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    Student getStudentById(int id);
    Student getStudentByName(String name);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
