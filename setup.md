This guide will walk you through the steps required to set up and configure the Chatbot application on your local machine.

Prerequisites
Before proceeding, ensure you have the following installed:

Java Development Kit (JDK) 17: The application is built using Java 17.
Apache Maven: Required for building and managing dependencies.
MySQL Database: The application uses MySQL for data storage.
Web Browser: To access the application's web interface.
Step 1: Clone the Repository
Clone the repository to your local machine:
bash
Insert Code
Run
Copy code
git clone https://github.com/your-repo/chatbot.git
Navigate to the project directory:
bash
Insert Code
Run
Copy code
cd chatbot
Step 2: Set Up the Database
Create the Database:

Log in to your MySQL server:
bash
Insert Code
Run
Copy code
mysql -u root -p
Create a new database named chatbot_db:
sql
Insert Code
Run
Copy code
CREATE DATABASE chatbot_db;
Exit the MySQL shell:
sql
Insert Code
Run
Copy code
exit;
Configure Database Connection:

Update the database connection details in DatabaseUtil.java:
java
Insert Code
Run
Copy code
private static final String URL = "jdbc:mysql://localhost:3306/chatbot_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
Step 3: Build the Application
Navigate to the project root directory (where pom.xml is located).
Build the project using Maven:
bash
Insert Code
Run
Copy code
mvn clean install
Step 4: Run the Application
Run the Main Class:

Execute the Main.java class to test the application:
bash
Insert Code
Run
Copy code
mvn exec:java -Dexec.mainClass="com.example.Main"
Deploy to a Servlet Container:

If you are using a servlet container like Apache Tomcat, deploy the generated WAR file to the server.
Step 5: Access the Web Interface
Start your servlet container (e.g., Tomcat).
Open a web browser and navigate to:
Insert Code
Run
Copy code
http://localhost:8080/chatbot
Use the web interface to manage users and view conversation history.
Step 6: Run Tests
Run the unit tests to ensure everything is working correctly:
bash
Insert Code
Run
Copy code
mvn test
Troubleshooting
Database Connection Issues:
Ensure MySQL is running and the credentials in DatabaseUtil.java are correct.
Build Failures:
Verify that all dependencies are correctly installed by running mvn clean install.
