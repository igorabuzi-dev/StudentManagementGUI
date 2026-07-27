Student Management System
A JavaFX desktop application for managing student records with efficient data structures and an intuitive user interface.


Overview
The Student Management System is a comprehensive desktop application built with JavaFX that allows educational institutions to efficiently manage student records. The application features CRUD operations (Create, Read, Update, Delete), efficient searching using HashMap (O(1) complexity), sorting capabilities, and real-time statistics.


Features

Core Functionality:
Add Students - Register new students with ID, name, surname, and grade
View Students - Display all students in an interactive table
Update Students - Modify existing student information
Delete Students - Remove students from the system
Search Students - Fast O(1) search by ID using HashMap
Sort Students - Sort by name (A-Z) or by average grade (highest first)


Statistics Dashboard

Total Students Counter - Real-time count of enrolled students
Overall Average - Calculates and displays the average grade of all students


Interactive Table

Click on any row to automatically populate form fields for editing
Real-time updates after every operation
Column headers for clear data organization


Technical Stack

Component	Technology
Language	Java 17+
GUI Framework	JavaFX
Data Structure	LinkedList + HashMap
Build Tool	Maven/Gradle (optional)
IDE	IntelliJ IDEA


📊 Data Structure Design

The application uses a hybrid data structure approach for optimal performance:
java
private final LinkedList<Student> studentet = new LinkedList<>();
private final Map<String, Student> studentMap = new HashMap<>();

Operation	Complexity	Data Structure Used
Add Student	O(1)	LinkedList + HashMap
Search by ID	O(1)	HashMap
Delete Student	O(n)	LinkedList
Update Student	O(1)	HashMap lookup + LinkedList update
Sort by Name	O(n²)	LinkedList (Bubble Sort)
Sort by Grade	O(n²)	LinkedList (Bubble Sort)
Get Total Students	O(1)	LinkedList.size()
Calculate Average	O(n)	LinkedList iteration
 

User Interface

Main Window Layout
Left Panel: Student form with input fields and action buttons
Right Panel: Interactive table showing all student records
Top Section: Statistics dashboard (total students & average grade)


Input Fields

ID: Unique student identifier (e.g., "STU01", "A12B")
Emri: Student's first name
Mbiemri: Student's surname
Mesatarja: Student's average grade (supports both "." and "," decimals)


Action Buttons

Shto: Add new student to the system
Fshi: Delete selected student
Perditeso: Update selected student's information
Kerko Student: Search for student by ID (O(1) performance)
Pastro fushat: Clear all input fields
Rendit: Sort students based on selected criteria


Installation & Setup


Prerequisites
Java Development Kit (JDK) 17 or higher
IntelliJ IDEA (or any Java IDE)
JavaFX SDK (if not bundled with JDK)


Steps to Run
Clone the repository:
git clone [your-repository-url]

Open in IntelliJ IDEA:
File → Open → Select project directory
Wait for project indexing to complete

Configure JavaFX (if needed):
Add VM options in Run Configuration:
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml


Run the application:

Navigate to Main.java
Right-click → Run 'Main.main()'

Usage Guide

Adding a Student
Fill in the ID, Emri, Mbiemri, and Mesatarja fields
Click Shto button
Student appears in the table and statistics update automatically

Searching for a Student
Enter the student ID in the ID field
Click Kerko Student
The student will be highlighted in the table if found

Updating a Student
Click on any row in the table to load data into fields
Modify the desired fields
Click Perditeso to save changes

Deleting a Student
Select a student from the table
Click Fshi to remove them

Sorting Students
Choose sort criteria from dropdown:
Emri (A–Z): Alphabetical order by name
Mesatarja (nga me e larta): Highest average first

Click Rendit to apply sorting


Code Structure

src/
└── com/studentmanagement/studentmanagementapp/
    ├── Main.java                    # Main JavaFX application
    │   ├── Student (inner class)    # Student model with getters/setters
    │   └── StudentManager           # Core logic (LinkedList + HashMap)
    └── resources/                   # Optional: CSS, FXML files
Key Methods
java
// StudentManager class
boolean shtoStudent(Student s)          // Add student
Student gjejStudent(String id)          // Find student (LinkedList)
Student gjejStudentMeMap(String id)     // Find student (HashMap - O(1))
boolean fshiStudent(String id)          // Delete student
boolean perditesoStudent(...)           // Update student
void sortByNameAZ()                     // Sort alphabetically
void sortByAverageDesc()                // Sort by grade descending
int numriTotalStudenteve()              // Get total count
double mesatarjaPergjithshme()          // Calculate average


Performance Optimization
HashMap for instant ID lookups: Searching by ID is O(1) regardless of dataset size
LinkedList for ordered storage: Maintains insertion order and supports efficient sorting
ObservableList for TableView: Automatic UI updates when data changes
Real-time statistics: Total count O(1), Average O(n) calculated on demand


Error Handling
Validates input fields before operations
Handles decimal separators (supports both "." and ",")
Provides user feedback through status messages
Prevents duplicate IDs
Graceful error handling for invalid inputs


Contributing
Fork the repository
Create a feature branch
Make your changes
Submit a pull request


Application Language
The application interface is currently available in Albanian.

Author
igorabuzi-dev
Bachelor Project – Software Engineering
