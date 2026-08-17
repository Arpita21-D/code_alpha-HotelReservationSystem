import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for the Student Grade Tracker Application
 * Provides a menu-driven interface for managing student grades
 */
public class GradeTrackerMain {
    private static StudentGradeTracker tracker;
    private static Scanner scanner;

    /**
     * Main method - Entry point of the application
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        tracker = new StudentGradeTracker();
        scanner = new Scanner(System.in);

        displayWelcome();
        displayMenu();
    }

    /**
     * Display the welcome message
     */
    public static void displayWelcome() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║    WELCOME TO STUDENT GRADE TRACKER APPLICATION        ║");
        System.out.println("║              Version 1.0                               ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
    }

    /**
     * Display the main menu and handle user input
     */
    public static void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("╔═══════════════════════════════════════════════════════╗");
            System.out.println("║                    MAIN MENU                          ║");
            System.out.println("╠═══════════════════════════════════════════════════════╣");
            System.out.println("║  1. Add Student                                       ║");
            System.out.println("║  2. Display Students                                  ║");
            System.out.println("║  3. Search Student                                    ║");
            System.out.println("║  4. Exit                                              ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice (1-4): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        running = false;
                        exitApplication();
                        break;
                    default:
                        System.out.println("\n❌ Invalid choice! Please enter a number between 1 and 4.\n");
                }
            } catch (Exception e) {
                System.out.println("\n❌ Invalid input! Please enter a valid number.\n");
                scanner.nextLine(); // Clear the buffer
            }
        }
    }

    /**
     * Add a new student to the system
     */
    public static void addStudent() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                 ADD NEW STUDENT                        ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");

        try {
            // Get student name
            System.out.print("\nEnter student name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("\n❌ Student name cannot be empty!\n");
                return;
            }

            // Get student ID
            System.out.print("Enter student ID: ");
            String studentID = scanner.nextLine().trim();

            if (studentID.isEmpty()) {
                System.out.println("\n❌ Student ID cannot be empty!\n");
                return;
            }

            // Create new student
            Student student = new Student(name, studentID);

            // Get number of subjects
            System.out.print("Enter number of subjects: ");
            int numSubjects = scanner.nextInt();

            if (numSubjects <= 0) {
                System.out.println("\n❌ Number of subjects must be greater than 0!\n");
                return;
            }

            scanner.nextLine(); // Consume newline

            // Input marks for each subject
            System.out.println("\nEnter marks for each subject (0-100):");

            for (int i = 0; i < numSubjects; i++) {
                while (true) {
                    try {
                        System.out.print("Subject " + (i + 1) + " marks: ");
                        double mark = scanner.nextDouble();

                        // Validate marks
                        if (mark < 0 || mark > 100) {
                            System.out.println("⚠️  Marks must be between 0 and 100! Please try again.");
                            continue;
                        }

                        student.addMark(mark);
                        break; // Move to next subject
                    } catch (Exception e) {
                        System.out.println("❌ Invalid input! Please enter a valid number.");
                        scanner.nextLine(); // Clear the buffer
                    }
                }
            }

            scanner.nextLine(); // Consume newline

            // Add student to tracker
            if (tracker.addStudent(student)) {
                System.out.println("\n✅ Student added successfully!\n");
                student.displayInfo();
            } else {
                System.out.println("\n❌ Student ID already exists! Please use a different ID.\n");
            }

        } catch (Exception e) {
            System.out.println("\n❌ An error occurred while adding student. Please try again.\n");
            scanner.nextLine(); // Clear the buffer
        }
    }

    /**
     * Display all students
     */
    public static void displayAllStudents() {
        if (tracker.getStudentCount() == 0) {
            System.out.println("\n⚠️  No students in the system yet.\n");
        } else {
            tracker.displayAllStudents();
        }
    }

    /**
     * Search for a student
     */
    public static void searchStudent() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║               SEARCH STUDENT                           ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║  1. Search by ID                                      ║");
        System.out.println("║  2. Search by Name                                    ║");
        System.out.println("║  3. Back to Menu                                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.print("\nEnter your choice (1-3): ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchByID();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    // Return to menu
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please enter a number between 1 and 3.\n");
            }
        } catch (Exception e) {
            System.out.println("\n❌ Invalid input! Please enter a valid number.\n");
            scanner.nextLine(); // Clear the buffer
        }
    }

    /**
     * Search for a student by ID
     */
    private static void searchByID() {
        System.out.print("Enter Student ID to search: ");
        String studentID = scanner.nextLine().trim();

        Student student = tracker.searchStudentByID(studentID);

        if (student != null) {
            System.out.println("✅ Student found!");
            student.displayInfo();
        } else {
            System.out.println("\n❌ No student found with ID: " + studentID + "\n");
        }
    }

    /**
     * Search for a student by name
     */
    private static void searchByName() {
        System.out.print("Enter Student Name to search (partial match allowed): ");
        String name = scanner.nextLine().trim();

        ArrayList<Student> results = tracker.searchStudentByName(name);

        if (!results.isEmpty()) {
            System.out.println("\n✅ Found " + results.size() + " student(s):");
            for (Student student : results) {
                student.displayInfo();
            }
        } else {
            System.out.println("\n❌ No student found with name: " + name + "\n");
        }
    }

    /**
     * Exit the application
     */
    public static void exitApplication() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║              Thank you for using                       ║");
        System.out.println("║         Student Grade Tracker Application              ║");
        System.out.println("║                   Goodbye!                             ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        scanner.close();
        System.exit(0);
    }
}
