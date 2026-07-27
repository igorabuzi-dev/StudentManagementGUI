# Student Management System

A JavaFX desktop application for managing student records using efficient data structures and an intuitive graphical user interface.

## Overview

Student Management System is a desktop application developed with JavaFX that helps educational institutions manage student information efficiently.

The application provides complete CRUD functionality (Create, Read, Update, Delete), fast searching using HashMap with O(1) complexity, sorting capabilities, and a statistics dashboard with real-time updates.

The project demonstrates the use of data structures, object-oriented programming principles, and JavaFX GUI development.

---

# Features

## Student Management

The system supports the following operations:
- Add new students
- View all students in an interactive table
- Update existing student information
- Delete students from the system
- Search students by ID
- Sort students by different criteria

### Student Information

Each student contains:
- Student ID
- First Name
- Last Name
- Average Grade

Example ID formats: K88524M


---

# Statistics Dashboard

The application provides real-time statistics:

## Total Students

Displays the current number of registered students.

## Overall Average

Calculates the average grade of all students stored in the system.

---

# User Interface

The application is organized into three main sections:

## Top Section

Statistics dashboard containing:
- Total number of students
- General average grade

## Left Panel

Student management form containing:
- ID input field
- First name input field
- Last name input field
- Average grade input field

Available actions:
- Add student
- Update student
- Delete student
- Search student
- Clear fields
- Sort students

## Right Panel

Interactive TableView displaying all student records.

Features:
- Select a row to automatically load student information
- Real-time table updates
- Organized columns for better visualization

---

# Data Structure Design

The application uses a hybrid data structure approach for optimized performance.
```java
private final LinkedList<Student> studentet = new LinkedList<>();
private final Map<String, Student> studentMap = new HashMap<>();
Data Structures Used
LinkedList

Used for:
Maintaining student order
Storing all student records
Sorting operations
HashMap

Used for:
Fast student lookup by ID
Improving search performance
Operation Complexity
Operation	Complexity	Data Structure
Add Student	O(1)	LinkedList + HashMap
Search by ID	O(1)	HashMap
Delete Student	O(n)	LinkedList
Update Student	O(1) lookup + O(n) update	HashMap + LinkedList
Sort by Name	O(n²)	LinkedList Bubble Sort
Sort by Average	O(n²)	LinkedList Bubble Sort
Get Total Students	O(1)	LinkedList.size()
Calculate Average	O(n)	LinkedList iteration
Technical Stack
Component	Technology
Programming Language	Java 17+
GUI Framework	JavaFX
Data Structures	LinkedList, HashMap
Build Tool	Maven / Gradle
IDE	IntelliJ IDEA
```
---
  ## Project Structure
```
src/
└── com/studentmanagement/studentmanagementapp/
    |
    ├── Main.java
    |
    ├── Student.java
    |
    ├── StudentManager.java
    |
    └── resources/
        |
        ├── CSS files
        └── FXML files (optional)
Main Components
Main.java
```
---

## Responsible for:

Starting the JavaFX application
Creating the main interface
Connecting UI components
Student.java

Student model class containing:
Attributes
Getters and setters
Student information management
StudentManager.java

Core application logic:
Managing LinkedList and HashMap
CRUD operations
Searching
Sorting
Statistics calculation

## Key Methods
boolean shtoStudent(Student s)
Student gjejStudentMeMap(String id)
boolean fshiStudent(String id)
boolean perditesoStudent(...)
void sortByNameAZ()
void sortByAverageDesc()
int numriTotalStudenteve()
double mesatarjaPergjithshme()
Installation and Setup
Requirements

Before running the project, make sure you have:
Java Development Kit (JDK) 17 or higher
IntelliJ IDEA or another Java IDE
JavaFX SDK (if not included)

Running the Application
1. Clone Repository
git clone [repository-url]

2. Open Project
Open the project using IntelliJ IDEA:
File → Open → Select Project Directory
Wait until indexing is completed.

3. Configure JavaFX
If JavaFX is not automatically configured, add VM options:
--module-path /path/to/javafx-sdk/lib 
--add-modules javafx.controls,javafx.fxml

4. Run Application

Navigate to:
Main.java
Run:
Main.main()

Usage Guide:

-Adding a Student
Enter student information:
ID
Name
Surname
Average grade
Click:
Shto
The student will appear in the table.
Statistics will update automatically.

-Searching for a Student
Enter student ID
Click:
Kerko Student
The application searches using HashMap with O(1) complexity.
Updating Student Information
Select a student from the table
Modify the information
Click:
Perditeso
Deleting a Student
Select a student
Click:
Fshi
The student will be removed from the system.

-Sorting Students
Available sorting options:
Name Sorting
Emri (A-Z)
Sorts students alphabetically.

-Grade Sorting
Mesatarja (nga me e larta)
Sorts students from highest to lowest average.

Error Handling
The application includes:
Input validation
Duplicate ID prevention
Decimal separator support (. and ,)
Invalid input handling
User feedback messages
Performance Optimization

## The application improves performance through:
-HashMap Searching
Student search by ID is performed in: O(1)making the lookup efficient even with large datasets.
-LinkedList Storage
Maintains student order and supports data manipulation.
-ObservableList Integration
JavaFX TableView automatically updates when data changes.
-Dynamic Statistics
Statistics are recalculated based on current data.

## Application Language
The application interface is currently available in **Albanian**.

## Author
**igorabuzi-dev**

**Bachelor Project – Software Engineering**
