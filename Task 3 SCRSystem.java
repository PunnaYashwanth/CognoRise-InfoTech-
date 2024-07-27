import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a course
class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getCapacity() { return capacity; }
    public int getEnrolled() { return enrolled; }

    public boolean isFull() { return enrolled >= capacity; }

    public void enroll() { if (!isFull()) enrolled++; }
    public void drop() { if (enrolled > 0) enrolled--; }
}

// Class to represent a student
class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<Course> getRegisteredCourses() { return registeredCourses; }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enroll();
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.drop();
        }
    }
}

// Main class to manage the registration system
public class CourseRegistrationSystem {

    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        // Sample data
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30));
        courses.add(new Course("CS102", "Data Structures", "Introduction to data structures", 25));

        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println("Code: " + course.getCode() + ", Title: " + course.getTitle()
                    + ", Description: " + course.getDescription() + ", Capacity: " + course.getCapacity()
                    + ", Enrolled: " + course.getEnrolled() + ", Slots Left: " + (course.getCapacity() - course.getEnrolled()));
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.isFull()) {
            System.out.println("Course is full. Cannot register.");
            return;
        }

        student.registerCourse(course);
        System.out.println("Registered for course: " + course.getTitle());
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
        System.out.println("Dropped course: " + course.getTitle());
    }

    private static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
