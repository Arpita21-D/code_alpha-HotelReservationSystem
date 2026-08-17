import java.util.ArrayList;

/**
 * Student class to store student information
 * Stores student name, ID, and marks for multiple subjects
 */
public class Student {
    private String name;
    private String studentID;
    private final ArrayList<Double> marks;

    /**
     * Constructor to initialize a Student object
     * 
     * @param name      the name of the student
     * @param studentID the unique ID of the student
     */
    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
        this.marks = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public ArrayList<Double> getMarks() {
        return marks;
    }

    /**
     * Add a mark for a subject
     * 
     * @param mark the mark to add (should be between 0-100)
     */
    public void addMark(double mark) {
        if (mark >= 0 && mark <= 100) {
            marks.add(mark);
        }
    }

    /**
     * Calculate total marks
     * 
     * @return sum of all marks
     */
    public double getTotalMarks() {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    /**
     * Calculate average marks
     * 
     * @return average of all marks, or 0 if no marks are present
     */
    public double getAverageMarks() {
        if (marks.isEmpty()) {
            return 0;
        }
        return getTotalMarks() / marks.size();
    }

    /**
     * Get grade based on average marks
     * Grading system:
     * 90-100 = A+
     * 80-89  = A
     * 70-79  = B
     * 60-69  = C
     * 50-59  = D
     * Below 50 = F
     * 
     * @return the grade as a String
     */
    public String getGrade() {
        double average = getAverageMarks();

        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * Display all student information in a formatted way
     */
    public void displayInfo() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║              STUDENT INFORMATION                       ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println("Name         : " + name);
        System.out.println("Student ID   : " + studentID);
        System.out.println("Number of Subjects: " + marks.size());

        if (!marks.isEmpty()) {
            System.out.println("\nMarks for each subject:");
            for (int i = 0; i < marks.size(); i++) {
                System.out.println("  Subject " + (i + 1) + " : " + marks.get(i));
            }
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println("Total Marks  : " + String.format("%.2f", getTotalMarks()));
            System.out.println("Average Marks: " + String.format("%.2f", getAverageMarks()));
            System.out.println("Grade        : " + getGrade());
        } else {
            System.out.println("\nNo marks added yet.");
        }
        System.out.println("═════════════════════════════════════════════════════════\n");
    }
}
