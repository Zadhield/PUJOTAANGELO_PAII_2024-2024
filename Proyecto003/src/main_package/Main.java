package main_package;

import java.sql.SQLException;
import model_package.Alumn;
import model_package.Conexion;
import model_package.Professor;
import model_package.Schedule;
import model_package.Subject;
/**
 * @ Angelo Pujota
 * @ Conexión a base de datos 
 */

public class Main {

    public static void main(String[] args) throws SQLException {

        // Para crear un nuevo alumno con un id distinto
        Alumn alumno = new Alumn(1, "Juan ", "Garcia", 25);
        

        // Método para crear la tabla de Alumnos
        alumno.createTable(Conexion.getConnection());
        alumno.create(Conexion.getConnection(), alumno);

        // Creamos un profesor con un id distinto
        Professor teacher = new Professor(1, "Byron", "Torres", 34);
   
       

        // Método para crear la tabla de Profesores
        teacher.createTable(Conexion.getConnection());
        teacher.create(Conexion.getConnection(), teacher);

        // Creamos una materia con un id distinto su nombre, descripción y el semestre en la que se cursa
        Subject subject = new Subject(1, "algebra lineal 2", "se encarga del estudio de matrices", 3);

        // Método para crear la tabla de Materias
        subject.createTable(Conexion.getConnection());
        subject.create(Conexion.getConnection(), subject);

        // Creamos un horario hora de inicio hora de fin y su dia
        Schedule sche = new Schedule(subject.getId(), alumno.getId(), teacher.getId(), "9", "11", "Lunes");

        // Método para crear la tabla de Horarios
        sche.createTable(Conexion.getConnection());
        sche.create(Conexion.getConnection(), sche);  
    }
}

