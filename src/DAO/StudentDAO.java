package DAO;

import Models.Student;
import Utils.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDAO {
    private static final String STUDENTS_FILE = "data/students.dat";
    private List<Student> students;
    
    public StudentDAO() {
        this.students = new ArrayList<>();
        loadStudents();
    }
    
    /**
     * Load students from file
     */
    private void loadStudents() {
        try {
            FileUtil.ensureDirectoryExists("data");
            students = FileUtil.loadFromFile(STUDENTS_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
            students = new ArrayList<>();
        }
    }
    
    /**
     * Save students to file
     */
    private void saveStudents() {
        try {
            FileUtil.ensureDirectoryExists("data");
            FileUtil.saveToFile(students, STUDENTS_FILE);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
    
    /**
     * Add a new student
     */
    public boolean addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            return false; // Student with this ID already exists
        }
        students.add(student);
        saveStudents();
        return true;
    }
    
    /**
     * Update an existing student
     */
    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                saveStudents();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Delete a student by ID
     */
    public boolean deleteStudent(int studentId) {
        boolean removed = students.removeIf(student -> student.getId() == studentId);
        if (removed) {
            saveStudents();
        }
        return removed;
    }
    
    /**
     * Find student by ID
     */
    public Student findStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Find students by name (partial match, case insensitive)
     */
    public List<Student> findStudentsByName(String name) {
        String searchName = name.toLowerCase();
        return students.stream()
                .filter(student -> student.getName().toLowerCase().contains(searchName))
                .collect(Collectors.toList());
    }
    
    /**
     * Find students by major
     */
    public List<Student> findStudentsByMajor(String major) {
        return students.stream()
                .filter(student -> student.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }
    
    /**
     * Find students by email
     */
    public Student findStudentByEmail(String email) {
        return students.stream()
                .filter(student -> student.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    /**
     * Get students enrolled in a specific course
     */
    public List<Student> getStudentsEnrolledInCourse(String courseId) {
        return students.stream()
                .filter(student -> student.isEnrolledInCourse(courseId))
                .collect(Collectors.toList());
    }
    
    /**
     * Get students with GPA above threshold
     */
    public List<Student> getStudentsWithGPAAbove(double threshold) {
        return students.stream()
                .filter(student -> student.getGpa() >= threshold)
                .collect(Collectors.toList());
    }
    
    /**
     * Get students by age range
     */
    public List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
        return students.stream()
                .filter(student -> student.getAge() >= minAge && student.getAge() <= maxAge)
                .collect(Collectors.toList());
    }
    
    /**
     * Get total number of students
     */
    public int getTotalStudents() {
        return students.size();
    }
    
    /**
     * Check if student exists by ID
     */
    public boolean studentExists(int id) {
        return findStudentById(id) != null;
    }
    
    /**
     * Enroll student in course
     */
    public boolean enrollStudentInCourse(int studentId, String courseId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            boolean enrolled = student.enrollInCourse(courseId);
            if (enrolled) {
                saveStudents();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Drop student from course
     */
    public boolean dropStudentFromCourse(int studentId, String courseId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            boolean dropped = student.dropCourse(courseId);
            if (dropped) {
                saveStudents();
                return true;
            }
        }
        return false;
    }
}
