# Dimaculangan_Final_Project_SafeMind

Project Overview:
SafeMind is a Mental Health Self-Check App designed to help users assess their mental well-being. It allows users to answer various types of questions, track their progress over time, and access mental health resources and helplines. The app will focus on user interaction and provide a simple yet effective tool for managing mental health, aiming to align with the United Nations' Sustainable Development Goal (SDG) of Good Health and Well-being (SDG 3).

OOP Principles:
Encapsulation:
Each question and user response will be encapsulated in objects. For example, a Question object will store properties like the text of the question and the type of response expected (e.g., multiple choice, rating).
Example: A MentalHealthQuestion class will contain methods to display the question, collect answers, and validate them.

Inheritance:
Different types of questions can be modeled using inheritance. For instance, a RatingQuestion class could inherit from the base MentalHealthQuestion class, but add additional functionality for ratings.
Example: YesNoQuestion and RateFrom1To5Question classes will inherit from MentalHealthQuestion, but implement specific behaviors for their types.

Polymorphism:
This principle will allow the app to handle different types of questions with the same method. The submitAnswer() method can be used for all questions, but it will behave differently depending on the question type (e.g., storing a numeric rating or boolean value).
Example: The submitAnswer() method in the base MentalHealthQuestion class could be overridden in subclasses to handle different data types for answers.

Abstraction:
Abstraction will allow the app to hide the complexities of the different question types from the user, providing a simple interface for interacting with the app. The user will only interact with high-level objects like Questionnaire or UserProfile, without worrying about the underlying details of each question type.
Example: The MentalHealthAssessment class abstracts the details of how questions are created, displayed, and scored, providing the user with a seamless experience.

Libraries and Concepts:

File Handling: To save and load user data and progress.
Libraries: java.io.* (e.g., FileWriter, BufferedReader) for saving user answers and loading previous sessions.
JavaFX: To create a simple and interactive GUI for the app.
Libraries: javafx.application.*, javafx.scene.*, javafx.event.* to build the user interface (e.g., buttons, forms, labels).
API Integration: For displaying mental health resources and helpline numbers.
Libraries: Use HttpURLConnection or third-party libraries like Retrofit for API calls to fetch external resources.
Object-Oriented Design: For modeling questions, users, and assessments using classes and objects.

SDG (Sustainable Development Goal):

SafeMind aligns with SDG 3: Good Health and Well-being, which focuses on promoting mental health and well-being for all at all ages. The app supports this goal by providing a tool for individuals to assess their mental health, access mental health resources, and track their well-being, thereby helping to reduce stigma and encourage seeking help when needed.


Instructions for the Program:

Welcome Message: Upon starting the program, you will see a welcoming message and be prompted to input your name and age.

Questionnaire: You will be asked a series of questions to assess your mental health:

A Multiple Choice Question asking about how you’re feeling today.
A Yes/No Question about whether you've been feeling stressed lately.
A Rate Question to rate your overall mental health in the past week.
The program will prompt you to respond by choosing an option, and it will automatically check if your input is valid (e.g., choosing a number between the valid options or entering "Yes"/"No").

Recording Responses: After answering the questions, your responses are recorded and saved in the system, and your score will be calculated based on your answers (e.g., choosing positive responses increases the score).

Results and Feedback: After completing the questionnaire, the program will display a summary of your answers and your score. It will also provide mental health feedback based on your score

Cheer-Up Message: Depending on your score, a motivating or cheering message will be displayed to encourage you.

Review Responses: After the results, you will be asked if you want to review your answers.

Retake the Test: You can choose to retake the test after reviewing your responses.

Saving Data: Your responses and score will be saved to a file (user_data.txt) for future reference.

Mental Health Resources: At the end of the session, the program will display mental health resources and helplines that you can reach out to.
