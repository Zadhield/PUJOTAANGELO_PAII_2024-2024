package repositories;

import models.Subject;

import java.util.List;

public interface SubjectDAO {
    void addSubject(Subject subject);
    Subject getSubjectById(int id);
    Subject getSubjectByName(String name);
    List<Subject> getAllSubjects();
    void updateSubject(Subject subject);
    void deleteSubject(Subject subject);
}
