package repositories;

import models.Professor;

import java.util.List;

public interface ProfessorDAO {
    void addProfessor(Professor professor);
    Professor getProfessorById(int id);
    Professor getProfessorByName(String name);
    List<Professor> getAllProfessors();
    void updateProfessor(Professor professor);
    void deleteProfessor(Professor professor);
}
