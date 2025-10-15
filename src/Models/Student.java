package Models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Private fields
    private String name;
    private int id;
    private String email;
    private int age;
    private String major;
    private double gpa;
    private List<String> enrolledCourses;

    // Default constructor
    public Student() {
        this.enrolledCourses = new ArrayList<>();
    }

    // Parameterized constructor
    public Student(String name, int id, String email, int age, String major, double gpa) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
        this.enrolledCourses = new ArrayList<>();
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public List<String> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setEnrolledCourses(List<String> enrolledCourses) {
        this.enrolledCourses = new ArrayList<>(enrolledCourses);
    }

    // Course enrollment methods
    public boolean enrollInCourse(String courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
            return true;
        }
        return false;
    }

    public boolean dropCourse(String courseId) {
        return enrolledCourses.remove(courseId);
    }

    public boolean isEnrolledInCourse(String courseId) {
        return enrolledCourses.contains(courseId);
    }

    public int getTotalEnrolledCourses() {
        return enrolledCourses.size();
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", gpa=" + gpa +
                ", enrolledCourses=" + enrolledCourses +
                '}';
    }
}
