import java.util.ArrayList;

/**
 * StudentGradeTracker class to manage a collection of students
 * Provides methods to add, display, and search for students
 */
public class StudentGradeTracker {
    private final ArrayList<Student> students;

    /**
     * Constructor to initialize the StudentGradeTracker
     */
    public StudentGradeTracker() {
        this.students = new ArrayList<>();
    }

    /**
     * Add a new student to the tracker
     * 
     * @param student the Student object to add
     * @return true if student is added, false if student ID already exists
     */
    public boolean addStudent(Student student) {
        // Check if student ID already exists
        for (Student s : students) {
            if (s.getStudentID().equals(student.getStudentID())) {
                return false; // Student ID already exists
            }
        }
        students.add(student);
        return true;
    }

    /**
     * Search for a student by ID
     * 
     * @param studentID the ID to search for
     * @return the Student object if found, null otherwise
     */
    public Student searchStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Search for a student by name
     * 
     * @param name the name to search for (partial match)
     * @return ArrayList of matching students
     */
    public ArrayList<Student> searchStudentByName(String name) {
        ArrayList<Student> results = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(student);
            }
        }
        return results;
    }

    /**
     * Display all students
     */
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\n⚠️  No students found in the system.\n");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      ALL STUDENTS INFORMATION                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

        for (int i = 0; i < students.size(); i++) {
            System.out.println("Student #" + (i + 1));
            System.out.println("─────────────────────────────────────────────────────────────────");
            Student student = students.get(i);
            System.out.println("Name        : " + student.getName());
            System.out.println("Student ID  : " + student.getStudentID());
            System.out.println("Subjects    : " + student.getMarks().size());

            if (!student.getMarks().isEmpty()) {
                System.out.println("Average     : " + String.format("%.2f", student.getAverageMarks()));
                System.out.println("Grade       : " + student.getGrade());
            } else {
                System.out.println("Average     : N/A (No marks added)");
                System.out.println("Grade       : N/A");
            }
            System.out.println();
        }

        System.out.println("════════════════════════════════════════════════════════════════════\n");
    }

    /**
     * Get the number of students in the tracker
     * 
     * @return the count of students
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Get all students
     * 
     * @return ArrayList of all students
     */
    public ArrayList<Student> getAllStudents() {
        return students;
    }
}
