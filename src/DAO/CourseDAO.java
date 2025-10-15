package DAO;

import Models.Course;
import Utils.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDAO {
    private static final String COURSES_FILE = "data/courses.dat";
    private List<Course> courses;
    
    public CourseDAO() {
        this.courses = new ArrayList<>();
        loadCourses();
    }
    
    /**
     * Load courses from file
     */
    private void loadCourses() {
        try {
            FileUtil.ensureDirectoryExists("data");
            courses = FileUtil.loadFromFile(COURSES_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading courses: " + e.getMessage());
            courses = new ArrayList<>();
        }
    }
    
    /**
     * Save courses to file
     */
    private void saveCourses() {
        try {
            FileUtil.ensureDirectoryExists("data");
            FileUtil.saveToFile(courses, COURSES_FILE);
        } catch (IOException e) {
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }
    
    /**
     * Add a new course
     */
    public boolean addCourse(Course course) {
        if (findCourseById(course.getCourseId()) != null) {
            return false; // Course with this ID already exists
        }
        courses.add(course);
        saveCourses();
        return true;
    }
    
    /**
     * Update an existing course
     */
    public boolean updateCourse(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(updatedCourse.getCourseId())) {
                courses.set(i, updatedCourse);
                saveCourses();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Delete a course by ID
     */
    public boolean deleteCourse(String courseId) {
        boolean removed = courses.removeIf(course -> course.getCourseId().equals(courseId));
        if (removed) {
            saveCourses();
        }
        return removed;
    }
    
    /**
     * Find course by ID
     */
    public Course findCourseById(String courseId) {
        return courses.stream()
                .filter(course -> course.getCourseId().equalsIgnoreCase(courseId))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Find courses by name (partial match, case insensitive)
     */
    public List<Course> findCoursesByName(String courseName) {
        String searchName = courseName.toLowerCase();
        return courses.stream()
                .filter(course -> course.getCourseName().toLowerCase().contains(searchName))
                .collect(Collectors.toList());
    }
    
    /**
     * Find courses by instructor
     */
    public List<Course> findCoursesByInstructor(String instructor) {
        return courses.stream()
                .filter(course -> course.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
    }
    
    /**
     * Find courses by credit hours
     */
    public List<Course> findCoursesByCredits(int credits) {
        return courses.stream()
                .filter(course -> course.getCredits() == credits)
                .collect(Collectors.toList());
    }
    
    /**
     * Get all courses
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }
    
    /**
     * Get available courses (with spots remaining)
     */
    public List<Course> getAvailableCourses() {
        return courses.stream()
                .filter(Course::hasAvailableSpots)
                .collect(Collectors.toList());
    }
    
    /**
     * Get courses with enrollment above threshold
     */
    public List<Course> getCoursesWithEnrollmentAbove(int threshold) {
        return courses.stream()
                .filter(course -> course.getCurrentEnrollment() >= threshold)
                .collect(Collectors.toList());
    }
    
    /**
     * Get courses by credit range
     */
    public List<Course> getCoursesByCreditsRange(int minCredits, int maxCredits) {
        return courses.stream()
                .filter(course -> course.getCredits() >= minCredits && course.getCredits() <= maxCredits)
                .collect(Collectors.toList());
    }
    
    /**
     * Get total number of courses
     */
    public int getTotalCourses() {
        return courses.size();
    }
    
    /**
     * Check if course exists by ID
     */
    public boolean courseExists(String courseId) {
        return findCourseById(courseId) != null;
    }
    
    /**
     * Increment course enrollment
     */
    public boolean incrementEnrollment(String courseId) {
        Course course = findCourseById(courseId);
        if (course != null && course.hasAvailableSpots()) {
            course.incrementEnrollment();
            saveCourses();
            return true;
        }
        return false;
    }
    
    /**
     * Decrement course enrollment
     */
    public boolean decrementEnrollment(String courseId) {
        Course course = findCourseById(courseId);
        if (course != null && course.getCurrentEnrollment() > 0) {
            course.decrementEnrollment();
            saveCourses();
            return true;
        }
        return false;
    }
    
    /**
     * Get courses sorted by enrollment (descending)
     */
    public List<Course> getCoursesSortedByEnrollment() {
        return courses.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getCurrentEnrollment(), c1.getCurrentEnrollment()))
                .collect(Collectors.toList());
    }
    
    /**
     * Get courses sorted by name
     */
    public List<Course> getCoursesSortedByName() {
        return courses.stream()
                .sorted((c1, c2) -> c1.getCourseName().compareToIgnoreCase(c2.getCourseName()))
                .collect(Collectors.toList());
    }
    
    /**
     * Get full courses (no available spots)
     */
    public List<Course> getFullCourses() {
        return courses.stream()
                .filter(course -> !course.hasAvailableSpots())
                .collect(Collectors.toList());
    }
}
