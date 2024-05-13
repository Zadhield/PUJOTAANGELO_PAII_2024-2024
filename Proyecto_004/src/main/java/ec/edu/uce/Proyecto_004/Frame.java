package ec.edu.uce.Proyecto_004;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Student;
import models.Professor;
import models.Subject;
import models.Schedule;
import repositories.StudentDAO;
import repositories.ProfessorDAO;
import repositories.SubjectDAO;
import repositories.ScheduleDAO;
import repositories.StudentDAOImpl;
import repositories.ProfessorDAOImpl;
import repositories.SubjectDAOImpl;
import repositories.ScheduleDAOImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Frame extends JFrame {

    private final StudentDAO studentRepository;
    private final ProfessorDAO professorRepository;
    private final SubjectDAO subjectRepository;
    private final ScheduleDAO scheduleRepository;

    private final JTable studentTable, professorTable, subjectTable, scheduleTable;
    private final DefaultTableModel studentTableModel, professorTableModel, subjectTableModel, scheduleTableModel;
    private final JTextField idField, nameField, lastNameField, ageField;
    private final JTextField professorIdField, professorNameField, professorLastNameField, professorAgeField;
    private final JTextField subjectIdField, subjectNameField, subjectDescriptionField, subjectLevelField;
    private final JTextField scheduleIdField, studentIdField, professorIdField_schedule, subjectIdField_schedule;
    private final JTextField startTimeField, endTimeField, dayField;

    public Frame(StudentDAO studentRepository, ProfessorDAO professorRepository, SubjectDAO subjectRepository, ScheduleDAO scheduleRepository) {
        super("Student CRUD App");
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.scheduleRepository = scheduleRepository;

        // Configuración de la ventana
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear campos de texto para Estudiantes
        idField = new JTextField(5);
        nameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(5);

        // Crear campos de texto para Profesores
        professorIdField = new JTextField(5);
        professorNameField = new JTextField(20);
        professorLastNameField = new JTextField(20);
        professorAgeField = new JTextField(5);

        // Crear campos de texto para Asignaturas
        subjectIdField = new JTextField(5);
        subjectNameField = new JTextField(20);
        subjectDescriptionField = new JTextField(20);
        subjectLevelField = new JTextField(5);

        // Crear campos de texto para Horarios
        scheduleIdField = new JTextField(5);
        studentIdField = new JTextField(5);
        professorIdField_schedule = new JTextField(5);
        subjectIdField_schedule = new JTextField(5);
        startTimeField = new JTextField(10);
        endTimeField = new JTextField(10);
        dayField = new JTextField(10);

        // Crear tablas
        studentTableModel = new DefaultTableModel();
        studentTable = new JTable(studentTableModel);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);

        professorTableModel = new DefaultTableModel();
        professorTable = new JTable(professorTableModel);
        JScrollPane professorScrollPane = new JScrollPane(professorTable);

        subjectTableModel = new DefaultTableModel();
        subjectTable = new JTable(subjectTableModel);
        JScrollPane subjectScrollPane = new JScrollPane(subjectTable);

        scheduleTableModel = new DefaultTableModel();
        scheduleTable = new JTable(scheduleTableModel);
        JScrollPane scheduleScrollPane = new JScrollPane(scheduleTable);

        // Botones para Estudiantes
        JButton addStudentButton = new JButton("Agregar Estudiante");
        JButton updateStudentButton = new JButton("Actualizar Estudiante");
        JButton deleteStudentButton = new JButton("Eliminar Estudiante");

        // Botones para Profesores
        JButton addProfessorButton = new JButton("Agregar Profesor");
        JButton updateProfessorButton = new JButton("Actualizar Profesor");
        JButton deleteProfessorButton = new JButton("Eliminar Profesor");

        // Botones para Asignaturas
        JButton addSubjectButton = new JButton("Agregar Asignatura");
        JButton updateSubjectButton = new JButton("Actualizar Asignatura");
        JButton deleteSubjectButton = new JButton("Eliminar Asignatura");

        // Botones para Horarios
        JButton addScheduleButton = new JButton("Agregar Horario");
        JButton updateScheduleButton = new JButton("Actualizar Horario");
        JButton deleteScheduleButton = new JButton("Eliminar Horario");

        // Configurar diseño de la ventana
        setLayout(new BorderLayout());

        // Panel para Estudiantes
        JPanel studentPanel = new JPanel(new BorderLayout());
        JPanel studentFormPanel = new JPanel(new GridLayout(2, 4));
        studentFormPanel.add(new JLabel("ID:"));
        studentFormPanel.add(idField);
        studentFormPanel.add(new JLabel("Nombre:"));
        studentFormPanel.add(nameField);
        studentFormPanel.add(new JLabel("Apellido:"));
        studentFormPanel.add(lastNameField);
        studentFormPanel.add(new JLabel("Edad:"));
        studentFormPanel.add(ageField);
        JPanel studentButtonPanel = new JPanel(new FlowLayout());
        studentButtonPanel.add(addStudentButton);
        studentButtonPanel.add(updateStudentButton);
        studentButtonPanel.add(deleteStudentButton);
        studentPanel.add(studentFormPanel, BorderLayout.NORTH);
        studentPanel.add(studentScrollPane, BorderLayout.CENTER);
        studentPanel.add(studentButtonPanel, BorderLayout.SOUTH);

        // Panel para Profesores
        JPanel professorPanel = new JPanel(new BorderLayout());
        JPanel professorFormPanel = new JPanel(new GridLayout(2, 4));
        professorFormPanel.add(new JLabel("ID:"));
        professorFormPanel.add(professorIdField);
        professorFormPanel.add(new JLabel("Nombre:"));
        professorFormPanel.add(professorNameField);
        professorFormPanel.add(new JLabel("Apellido:"));
        professorFormPanel.add(professorLastNameField);
        professorFormPanel.add(new JLabel("Edad:"));
        professorFormPanel.add(professorAgeField);
        JPanel professorButtonPanel = new JPanel(new FlowLayout());
        professorButtonPanel.add(addProfessorButton);
        professorButtonPanel.add(updateProfessorButton);
        professorButtonPanel.add(deleteProfessorButton);
        professorPanel.add(professorFormPanel, BorderLayout.NORTH);
        professorPanel.add(professorScrollPane, BorderLayout.CENTER);
        professorPanel.add(professorButtonPanel, BorderLayout.SOUTH);

        // Panel para Asignaturas
        JPanel subjectPanel = new JPanel(new BorderLayout());
        JPanel subjectFormPanel = new JPanel(new GridLayout(2, 4));
        subjectFormPanel.add(new JLabel("ID:"));
        subjectFormPanel.add(subjectIdField);
        subjectFormPanel.add(new JLabel("Nombre:"));
        subjectFormPanel.add(subjectNameField);
        subjectFormPanel.add(new JLabel("Descripción:"));
        subjectFormPanel.add(subjectDescriptionField);
        subjectFormPanel.add(new JLabel("Nivel:"));
        subjectFormPanel.add(subjectLevelField);
        JPanel subjectButtonPanel = new JPanel(new FlowLayout());
        subjectButtonPanel.add(addSubjectButton);
        subjectButtonPanel.add(updateSubjectButton);
        subjectButtonPanel.add(deleteSubjectButton);
        subjectPanel.add(subjectFormPanel, BorderLayout.NORTH);
        subjectPanel.add(subjectScrollPane, BorderLayout.CENTER);
        subjectPanel.add(subjectButtonPanel, BorderLayout.SOUTH);

        // Panel para Horarios
        JPanel schedulePanel = new JPanel(new BorderLayout());
        JPanel scheduleFormPanel = new JPanel(new GridLayout(2, 4));
        scheduleFormPanel.add(new JLabel("ID:"));
        scheduleFormPanel.add(scheduleIdField);
        scheduleFormPanel.add(new JLabel("ID Estudiante:"));
        scheduleFormPanel.add(studentIdField);
        scheduleFormPanel.add(new JLabel("ID Profesor:"));
        scheduleFormPanel.add(professorIdField_schedule);
        scheduleFormPanel.add(new JLabel("ID Asignatura:"));
        scheduleFormPanel.add(subjectIdField_schedule);
        scheduleFormPanel.add(new JLabel("Hora de inicio:"));
        scheduleFormPanel.add(startTimeField);
        scheduleFormPanel.add(new JLabel("Hora de fin:"));
        scheduleFormPanel.add(endTimeField);
        scheduleFormPanel.add(new JLabel("Día:"));
        scheduleFormPanel.add(dayField);
        JPanel scheduleButtonPanel = new JPanel(new FlowLayout());
        scheduleButtonPanel.add(addScheduleButton);
        scheduleButtonPanel.add(updateScheduleButton);
        scheduleButtonPanel.add(deleteScheduleButton);
        schedulePanel.add(scheduleFormPanel, BorderLayout.NORTH);
        schedulePanel.add(scheduleScrollPane, BorderLayout.CENTER);
        schedulePanel.add(scheduleButtonPanel, BorderLayout.SOUTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Estudiantes", studentPanel);
        tabbedPane.addTab("Profesores", professorPanel);
        tabbedPane.addTab("Asignaturas", subjectPanel);
        tabbedPane.addTab("Horarios", schedulePanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Cargar datos al iniciar la aplicación
        loadStudentData();
        loadProfessorData();
        loadSubjectData();
        loadScheduleData();

        // Agregar listeners a los botones de Estudiantes
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        // Agregar listeners a los botones de Profesores
        addProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProfessor();
            }
        });

        updateProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfessor();
            }
        });

        deleteProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProfessor();
            }
        });

        // Agregar listeners a los botones de Asignaturas
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSubject();
            }
        });

        updateSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSubject();
            }
        });

        deleteSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSubject();
            }
        });

        // Agregar listeners a los botones de Horarios
        addScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSchedule();
            }
        });

        updateScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSchedule();
            }
        });

        deleteScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSchedule();
            }
        });
    }

    private void loadStudentData() {
        List<Student> students;
        students = studentRepository.getAllStudents();

        studentTableModel.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Apellido", "Edad"});
        studentTableModel.setRowCount(0);

        for (Student student : students) {
            studentTableModel.addRow(new Object[]{student.getId(), student.getName(), student.getLastname(), student.getAge()});
        }
    }

    private void loadProfessorData() {
        List<Professor> professors;
        professors = professorRepository.getAllProfessors();

        professorTableModel.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Apellido", "Edad"});
        professorTableModel.setRowCount(0);

        for (Professor professor : professors) {
            professorTableModel.addRow(new Object[]{professor.getId(), professor.getName(), professor.getLastname(), professor.getAge()});
        }
    }

    private void loadSubjectData() {
        List<Subject> subjects;
        subjects = subjectRepository.getAllSubjects();

        subjectTableModel.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Descripción", "Nivel"});
        subjectTableModel.setRowCount(0);

        for (Subject subject : subjects) {
            subjectTableModel.addRow(new Object[]{subject.getId(), subject.getName(), subject.getDescription(), subject.getLevel()});
        }
    }

    private void loadScheduleData() {
        List<Schedule> schedules;
        schedules = scheduleRepository.getAllSchedules();

        scheduleTableModel.setColumnIdentifiers(new Object[]{"ID", "ID Estudiante", "ID Profesor", "ID Asignatura", "Hora de inicio", "Hora de fin", "Día"});
        scheduleTableModel.setRowCount(0);

        for (Schedule schedule : schedules) {
            scheduleTableModel.addRow(new Object[]{schedule.getId(), schedule.getStudent().getId(), schedule.getProfessor().getId(), schedule.getSubject().getId(), schedule.getStartTime(), schedule.getEndTime(), schedule.getDay()});
        }
    }

    private void addStudent() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());

        Student student = new Student(id, name, lastName, age);
        studentRepository.addStudent(student);

        loadStudentData();
    }

    private void updateStudent() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());

        Student student = new Student(id, name, lastName, age);
        studentRepository.updateStudent(student);

        loadStudentData();
    }

    private void deleteStudent() {
        int id = Integer.parseInt(idField.getText());
        Student student = studentRepository.getStudentById(id);
        if (student != null) {
            studentRepository.deleteStudent(student);
            loadStudentData();
        } else {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProfessor() {
        int id = Integer.parseInt(professorIdField.getText());
        String name = professorNameField.getText();
        String lastName = professorLastNameField.getText();
        int age = Integer.parseInt(professorAgeField.getText());

        Professor professor = new Professor(id, name, lastName, age);
        professorRepository.addProfessor(professor);

        loadProfessorData();
    }

    private void updateProfessor() {
        int id = Integer.parseInt(professorIdField.getText());
        String name = professorNameField.getText();
        String lastName = professorLastNameField.getText();
        int age = Integer.parseInt(professorAgeField.getText());

        Professor professor = new Professor(id, name, lastName, age);
        professorRepository.updateProfessor(professor);

        loadProfessorData();
    }

    private void deleteProfessor() {
        int id = Integer.parseInt(professorIdField.getText());
        Professor professor = professorRepository.getProfessorById(id);
        if (professor != null) {
            professorRepository.deleteProfessor(professor);
            loadProfessorData();
        } else {
            JOptionPane.showMessageDialog(this, "Profesor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSubject() {
        int id = Integer.parseInt(subjectIdField.getText());
        String name = subjectNameField.getText();
        String description = subjectDescriptionField.getText();
        String level = subjectLevelField.getText();

        Subject subject = new Subject(id, name, description, level);
        subjectRepository.addSubject(subject);

        loadSubjectData();
    }

    private void updateSubject() {
        int id = Integer.parseInt(subjectIdField.getText());
        String name = subjectNameField.getText();
        String description = subjectDescriptionField.getText();
        String level = subjectLevelField.getText();

        Subject subject = new Subject(id, name, description, level);
        subjectRepository.updateSubject(subject);

        loadSubjectData();
    }

    private void deleteSubject() {
        int id = Integer.parseInt(subjectIdField.getText());
        Subject subject = subjectRepository.getSubjectById(id);
        if (subject != null) {
            subjectRepository.deleteSubject(subject);
            loadSubjectData();
        } else {
            JOptionPane.showMessageDialog(this, "Asignatura no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSchedule() {
        int id = Integer.parseInt(scheduleIdField.getText());
        int studentId = Integer.parseInt(studentIdField.getText());
        int professorId = Integer.parseInt(professorIdField_schedule.getText());
        int subjectId = Integer.parseInt(subjectIdField_schedule.getText());
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String day = dayField.getText();

        Student student = studentRepository.getStudentById(studentId);
        Professor professor = professorRepository.getProfessorById(professorId);
        Subject subject = subjectRepository.getSubjectById(subjectId);

        Schedule schedule = new Schedule(id, student, professor, subject, startTime, endTime, day);
        scheduleRepository.addSchedule(schedule);

        loadScheduleData();
    }

    private void updateSchedule() {
        int id = Integer.parseInt(scheduleIdField.getText());
        int studentId = Integer.parseInt(studentIdField.getText());
        int professorId = Integer.parseInt(professorIdField_schedule.getText());
        int subjectId = Integer.parseInt(subjectIdField_schedule.getText());
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String day = dayField.getText();

        Student student = studentRepository.getStudentById(studentId);
        Professor professor = professorRepository.getProfessorById(professorId);
        Subject subject = subjectRepository.getSubjectById(subjectId);

        Schedule schedule = new Schedule(id, student, professor, subject, startTime, endTime, day);
        scheduleRepository.updateSchedule(schedule);

        loadScheduleData();
    }

    private void deleteSchedule() {
        int id = Integer.parseInt(scheduleIdField.getText());
        Schedule schedule = scheduleRepository.getScheduleById(id);
        if (schedule != null) {
            scheduleRepository.deleteSchedule(schedule);
            loadScheduleData();
        } else {
            JOptionPane.showMessageDialog(this, "Horario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }  
}
