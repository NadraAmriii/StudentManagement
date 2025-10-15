import Models.Student;
import Models.Course;
import DAO.StudentDAO;
import DAO.CourseDAO;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private Scanner scanner;

    public StudentManagementSystem() {
        this.studentDAO = new StudentDAO();
        this.courseDAO = new CourseDAO();
        this.scanner = new Scanner(System.in);
        
        // Initialize with sample data if no data exists
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Add sample courses if none exist
        if (courseDAO.getTotalCourses() == 0) {
            courseDAO.addCourse(new Course("CS101", "Introduction to Computer Science", 3, "Dr. Smith", 30));
            courseDAO.addCourse(new Course("MATH201", "Calculus II", 4, "Dr. Johnson", 25));
            courseDAO.addCourse(new Course("PHYS101", "Physics I", 4, "Dr. Brown", 20));
            courseDAO.addCourse(new Course("ENG102", "English Composition", 3, "Prof. Davis", 35));
            courseDAO.addCourse(new Course("HIST150", "World History", 3, "Dr. Wilson", 40));
            System.out.println("Sample courses initialized.");
        }
        
        // Add sample students if none exist
        if (studentDAO.getTotalStudents() == 0) {
            studentDAO.addStudent(new Student("John Doe", 1001, "john.doe@university.edu", 20, "Computer Science", 3.7));
            studentDAO.addStudent(new Student("Jane Smith", 1002, "jane.smith@university.edu", 19, "Mathematics", 3.9));
            studentDAO.addStudent(new Student("Mike Johnson", 1003, "mike.johnson@university.edu", 21, "Physics", 3.5));
            studentDAO.addStudent(new Student("Sarah Davis", 1004, "sarah.davis@university.edu", 20, "English", 3.8));
            System.out.println("Sample students initialized.");
        }
    }

    public void run() {
        System.out.println("=== Student Management System ===");
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    courseMenu();
                    break;
                case 3:
                    enrollmentMenu();
                    break;
                case 4:
                    reportsMenu();
                    break;
                case 5:
                    System.out.println("Thank you for using Student Management System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Reports");
        System.out.println("5. Exit");
    }

    private void studentMenu() {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Students");
            System.out.println("5. List All Students");
            System.out.println("6. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    searchStudents();
                    break;
                case 5:
                    listAllStudents();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void courseMenu() {
        while (true) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Search Courses");
            System.out.println("5. List All Courses");
            System.out.println("6. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    updateCourse();
                    break;
                case 3:
                    deleteCourse();
                    break;
                case 4:
                    searchCourses();
                    break;
                case 5:
                    listAllCourses();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void enrollmentMenu() {
        while (true) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Drop Student from Course");
            System.out.println("3. View Student's Enrolled Courses");
            System.out.println("4. View Course Enrollment");
            System.out.println("5. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    dropStudent();
                    break;
                case 3:
                    viewStudentCourses();
                    break;
                case 4:
                    viewCourseEnrollment();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        String name = getStringInput("Enter student name: ");
        int id = getIntInput("Enter student ID: ");
        
        if (studentDAO.studentExists(id)) {
            System.out.println("Student with ID " + id + " already exists!");
            return;
        }
        
        String email = getStringInput("Enter email: ");
        int age = getIntInput("Enter age: ");
        String major = getStringInput("Enter major: ");
        double gpa = getDoubleInput("Enter GPA: ");
        
        Student student = new Student(name, id, email, age, major, gpa);
        if (studentDAO.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    private void addCourse() {
        System.out.println("\n--- Add New Course ---");
        String courseId = getStringInput("Enter course ID: ");
        
        if (courseDAO.courseExists(courseId)) {
            System.out.println("Course with ID " + courseId + " already exists!");
            return;
        }
        
        String courseName = getStringInput("Enter course name: ");
        int credits = getIntInput("Enter credits: ");
        String instructor = getStringInput("Enter instructor: ");
        int maxCapacity = getIntInput("Enter max capacity: ");
        
        Course course = new Course(courseId, courseName, credits, instructor, maxCapacity);
        if (courseDAO.addCourse(course)) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Failed to add course.");
        }
    }

    private void enrollStudent() {
        System.out.println("\n--- Enroll Student in Course ---");
        int studentId = getIntInput("Enter student ID: ");
        Student student = studentDAO.findStudentById(studentId);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("Available courses:");
        List<Course> availableCourses = courseDAO.getAvailableCourses();
        for (Course course : availableCourses) {
            System.out.println(course.getCourseId() + " - " + course.getCourseName() + 
                             " (Capacity: " + course.getCurrentEnrollment() + "/" + course.getMaxCapacity() + ")");
        }
        
        String courseId = getStringInput("Enter course ID to enroll in: ");
        Course course = courseDAO.findCourseById(courseId);
        
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        
        if (!course.hasAvailableSpots()) {
            System.out.println("Course is full!");
            return;
        }
        
        if (student.isEnrolledInCourse(courseId)) {
            System.out.println("Student is already enrolled in this course!");
            return;
        }
        
        if (studentDAO.enrollStudentInCourse(studentId, courseId) && 
            courseDAO.incrementEnrollment(courseId)) {
            System.out.println("Student enrolled successfully!");
        } else {
            System.out.println("Failed to enroll student.");
        }
    }

    private void listAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private void listAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseDAO.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    private void reportsMenu() {
        while (true) {
            System.out.println("\n--- Reports ---");
            System.out.println("1. Students with High GPA (>= 3.5)");
            System.out.println("2. Popular Courses (by enrollment)");
            System.out.println("3. Students by Major");
            System.out.println("4. Course Availability");
            System.out.println("5. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    showHighGPAStudents();
                    break;
                case 2:
                    showPopularCourses();
                    break;
                case 3:
                    showStudentsByMajor();
                    break;
                case 4:
                    showCourseAvailability();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showHighGPAStudents() {
        System.out.println("\n--- Students with GPA >= 3.5 ---");
        List<Student> students = studentDAO.getStudentsWithGPAAbove(3.5);
        if (students.isEmpty()) {
            System.out.println("No students found with GPA >= 3.5");
        } else {
            for (Student student : students) {
                System.out.println(student.getName() + " (ID: " + student.getId() + 
                                 ", GPA: " + student.getGpa() + ")");
            }
        }
    }

    private void showPopularCourses() {
        System.out.println("\n--- Courses by Enrollment ---");
        List<Course> courses = courseDAO.getCoursesSortedByEnrollment();
        for (Course course : courses) {
            System.out.println(course.getCourseName() + " (" + course.getCourseId() + 
                             ") - Enrollment: " + course.getCurrentEnrollment() + 
                             "/" + course.getMaxCapacity());
        }
    }

    private void showStudentsByMajor() {
        String major = getStringInput("Enter major to search: ");
        List<Student> students = studentDAO.findStudentsByMajor(major);
        if (students.isEmpty()) {
            System.out.println("No students found in major: " + major);
        } else {
            System.out.println("\n--- Students in " + major + " ---");
            for (Student student : students) {
                System.out.println(student.getName() + " (ID: " + student.getId() + 
                                 ", GPA: " + student.getGpa() + ")");
            }
        }
    }

    private void showCourseAvailability() {
        System.out.println("\n--- Course Availability ---");
        List<Course> courses = courseDAO.getAllCourses();
        for (Course course : courses) {
            int available = course.getMaxCapacity() - course.getCurrentEnrollment();
            String status = available > 0 ? "Available (" + available + " spots)" : "Full";
            System.out.println(course.getCourseName() + " (" + course.getCourseId() + 
                             ") - " + status);
        }
    }

    // Utility methods for input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Placeholder methods for remaining functionality
    private void updateStudent() { System.out.println("Update Student feature - To be implemented"); }
    private void deleteStudent() { System.out.println("Delete Student feature - To be implemented"); }
    private void searchStudents() { System.out.println("Search Students feature - To be implemented"); }
    private void updateCourse() { System.out.println("Update Course feature - To be implemented"); }
    private void deleteCourse() { System.out.println("Delete Course feature - To be implemented"); }
    private void searchCourses() { System.out.println("Search Courses feature - To be implemented"); }
    private void dropStudent() { System.out.println("Drop Student feature - To be implemented"); }
    private void viewStudentCourses() { System.out.println("View Student Courses feature - To be implemented"); }
    private void viewCourseEnrollment() { System.out.println("View Course Enrollment feature - To be implemented"); }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.run();
    }
}
