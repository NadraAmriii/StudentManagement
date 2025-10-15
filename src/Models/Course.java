package Models;
import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String courseId;
    private String courseName;
    private int credits;
    private String instructor;
    private int maxCapacity;
    private int currentEnrollment;

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(String courseId, String courseName, int credits, String instructor, int maxCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    // Getter methods
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    // Setter methods
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

    // Business methods
    public boolean hasAvailableSpots() {
        return currentEnrollment < maxCapacity;
    }

    public void incrementEnrollment() {
        if (hasAvailableSpots()) {
            currentEnrollment++;
        }
    }

    public void decrementEnrollment() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", instructor='" + instructor + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", currentEnrollment=" + currentEnrollment +
                '}';
    }
}
