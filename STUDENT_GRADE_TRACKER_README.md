# Student Grade Tracker Application

## Overview
A beginner-friendly Java console application that allows you to manage student information and track their grades. The application stores student details, their marks for multiple subjects, and automatically calculates total marks, average marks, and assigns grades based on a predefined grading system.

## Features
✅ Add multiple students with their ID and name  
✅ Record marks for multiple subjects per student  
✅ Automatic calculation of total and average marks  
✅ Automatic grade assignment based on average  
✅ Display all students with their complete information  
✅ Search students by ID or Name  
✅ Input validation for marks (0-100)  
✅ Prevent duplicate student IDs  
✅ User-friendly menu-driven interface  

## Grading System
- **A+**: 90-100
- **A**: 80-89
- **B**: 70-79
- **C**: 60-69
- **D**: 50-59
- **F**: Below 50

## Project Structure
```
StudentGradeTracker/
├── Student.java              (Student class - stores student data)
├── StudentGradeTracker.java  (Tracker class - manages student collection)
├── GradeTrackerMain.java     (Main class - user interface and menu)
└── README.md                 (This file)
```

## System Requirements
- Java 8 or higher
- VS Code with Java Extension Pack installed
- No external libraries required

## How to Compile and Run

### Method 1: Using VS Code (Recommended)

**Step 1: Open the folder in VS Code**
```
1. Open VS Code
2. File → Open Folder
3. Navigate to: c:\Users\Amit\OneDrive\Desktop\CodeAlpha\HotelReservationSystem
4. Click "Select Folder"
```

**Step 2: Compile the Java files**
```
1. Open Terminal in VS Code (Ctrl + `)
2. Run the following command:
   javac Student.java StudentGradeTracker.java GradeTrackerMain.java
3. You should see no errors
```

**Step 3: Run the application**
```
1. In the same terminal, run:
   java GradeTrackerMain
2. The application will start and display the welcome menu
```

### Method 2: Using Command Prompt

**Step 1: Open Command Prompt**
```
1. Press Windows + R
2. Type: cmd
3. Press Enter
```

**Step 2: Navigate to the project folder**
```
cd c:\Users\Amit\OneDrive\Desktop\CodeAlpha\HotelReservationSystem
```

**Step 3: Compile the Java files**
```
javac Student.java StudentGradeTracker.java GradeTrackerMain.java
```

**Step 4: Run the application**
```
java GradeTrackerMain
```

### Method 3: Using VS Code Run Button

If you have the Java Extension Pack installed:
1. Open `GradeTrackerMain.java`
2. Click the "Run" button in the top-right corner
3. The program will compile and run automatically

## Usage Guide

### Main Menu Options

**1. Add Student**
- Enter student name (e.g., "John Doe")
- Enter student ID (e.g., "S001")
- Enter number of subjects
- Enter marks for each subject (must be between 0-100)
- System validates all inputs
- Student is added if ID is unique

**2. Display Students**
- Shows all students currently in the system
- Displays name, ID, number of subjects, average marks, and grade
- Shows formatted output for easy reading

**3. Search Student**
- **Search by ID**: Enter exact student ID to find a student
- **Search by Name**: Enter partial name (case-insensitive) to find matching students
- Displays full student information including all marks

**4. Exit**
- Safely exits the application

## Example Usage

```
WELCOME TO STUDENT GRADE TRACKER APPLICATION
        Version 1.0

MAIN MENU
1. Add Student
2. Display Students
3. Search Student
4. Exit

Enter your choice (1-4): 1

ADD NEW STUDENT
Enter student name: Alice Johnson
Enter student ID: S001
Enter number of subjects: 3
Enter marks for each subject (0-100):
Subject 1 marks: 95
Subject 2 marks: 87
Subject 3 marks: 92

✅ Student added successfully!

STUDENT INFORMATION
Name         : Alice Johnson
Student ID   : S001
Number of Subjects: 3
Marks for each subject:
  Subject 1 : 95.0
  Subject 2 : 87.0
  Subject 3 : 92.0
─────────────────────────────────────────────────────────
Total Marks  : 274.00
Average Marks: 91.33
Grade        : A+
```

## Input Validation
- ✓ Student name cannot be empty
- ✓ Student ID cannot be empty
- ✓ Student ID must be unique
- ✓ Number of subjects must be greater than 0
- ✓ Marks must be between 0 and 100
- ✓ Marks must be numeric values
- ✓ Menu choices must be valid numbers

## Code Highlights

### Student.java
- Stores individual student information
- Calculates total, average, and grade
- Displays formatted student information
- ~130 lines with comprehensive comments

### StudentGradeTracker.java
- Manages a collection of students
- Provides add, search, and display functionality
- Prevents duplicate student IDs
- Supports search by ID and name
- ~100 lines with comprehensive comments

### GradeTrackerMain.java
- Provides menu-driven interface
- Handles user input and validation
- Manages application flow
- ~250 lines with comprehensive comments and user-friendly messages

## Key Features of Implementation

1. **ArrayList Collection**: Uses ArrayList for dynamic student storage
2. **Input Validation**: Comprehensive validation for all user inputs
3. **Error Handling**: Try-catch blocks for handling invalid inputs
4. **User-Friendly Interface**: Clear menu structure and formatted output
5. **Well-Commented Code**: Every method and important section is commented
6. **No External Libraries**: Pure Java using only standard library
7. **Best Practices**: Follows Java naming conventions and OOP principles

## Troubleshooting

**Problem**: "javac is not recognized as an internal or external command"
- **Solution**: Java is not installed or not in PATH. Download Java JDK from oracle.com and install it.

**Problem**: "error: class X is public, should be declared in a file named X.java"
- **Solution**: Make sure each public class is in its own file with the same name as the class.

**Problem**: "error: cannot find symbol"
- **Solution**: Make sure all three Java files are in the same directory before compiling.

**Problem**: File not found error
- **Solution**: Make sure you're in the correct directory (c:\Users\Amit\OneDrive\Desktop\CodeAlpha\HotelReservationSystem) when running commands.

## Technical Details

- **Language**: Java
- **Java Version**: 8+
- **Data Structure**: ArrayList<Student>
- **Architecture**: Object-Oriented Design
- **Paradigm**: Console Application

## Future Enhancements (Optional)
- Save/load student data to file
- Edit existing student records
- Delete student records
- Export grades to CSV
- GUI using JavaFX or Swing

## Author Notes
This is a complete, production-ready console application suitable for college assignments or internship projects. All code follows best practices and includes comprehensive comments for educational purposes.

## License
Open source - Free to use and modify for educational purposes.

---

**Version**: 1.0  
**Last Updated**: 2026  
**Status**: Complete and Tested ✅
